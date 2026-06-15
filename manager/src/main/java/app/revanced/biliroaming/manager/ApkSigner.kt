package app.revanced.biliroaming.manager

import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.math.BigInteger
import java.security.KeyPairGenerator
import java.security.MessageDigest
import java.security.SecureRandom
import java.security.Signature
import java.security.cert.X509Certificate
import java.util.Date
import java.util.jar.Attributes
import java.util.jar.JarEntry
import java.util.jar.JarFile
import java.util.jar.JarOutputStream
import java.util.jar.Manifest
import org.bouncycastle.cert.jcajce.JcaX509CertificateConverter
import org.bouncycastle.cert.jcajce.JcaX509v3CertificateBuilder
import org.bouncycastle.cms.CMSProcessableByteArray
import org.bouncycastle.cms.CMSSignedDataGenerator
import org.bouncycastle.cms.jcajce.JcaSignerInfoGeneratorBuilder
import org.bouncycastle.cms.jcajce.JcaCertStore
import org.bouncycastle.operator.jcajce.JcaContentSignerBuilder
import org.bouncycastle.operator.jcajce.JcaDigestCalculatorProviderBuilder
import org.bouncycastle.asn1.x500.X500Name

/**
 * APK V1 签名器 — BouncyCastle CMS 标准实现
 * bcprov + bcpkix 在 Android 运行时已内置
 */
object ApkSigner {

    fun sign(input: File, output: File) {
        val keyGen = KeyPairGenerator.getInstance("RSA").apply { initialize(2048, SecureRandom()) }
        val keyPair = keyGen.generateKeyPair()

        val issuer = X500Name("CN=BiliRoamingX")
        val subject = X500Name("CN=BiliRoamingX")
        val serial = BigInteger.valueOf(System.currentTimeMillis())
        val notBefore = Date(System.currentTimeMillis() - 86400000L)
        val notAfter = Date(System.currentTimeMillis() + 365L * 86400000L * 10)

        val certBuilder = JcaX509v3CertificateBuilder(issuer, serial, notBefore, notAfter, subject, keyPair.public)
        val certSigner = JcaContentSignerBuilder("SHA256WithRSA").build(keyPair.private)
        val cert = JcaX509CertificateConverter().getCertificate(certBuilder.build(certSigner))
        val certList = listOf(cert)

        val mf = Manifest()
        mf.mainAttributes.putValue("Manifest-Version", "1.0")
        mf.mainAttributes.putValue("Created-By", "BiliRoamingX-Manager")
        JarFile(input).use { jar ->
            jar.entries().asSequence().forEach { entry ->
                if (entry.isDirectory || entry.name.startsWith("META-INF/")) return@forEach
                val md = MessageDigest.getInstance("SHA-256")
                jar.getInputStream(entry).use { s ->
                    val buf = ByteArray(8192); var len: Int
                    while (s.read(buf).also { len = it } != -1) md.update(buf, 0, len)
                }
                mf.entries[entry.name] = Attributes().apply { putValue("SHA-256-Digest", b64(md.digest())) }
            }
        }
        val mfBytes = ByteArrayOutputStream().use { mf.write(it); it.toByteArray() }

        val mfDigest = MessageDigest.getInstance("SHA-256").digest(mfBytes)
        val sf = Manifest()
        sf.mainAttributes.apply {
            putValue("Signature-Version", "1.0")
            putValue("Created-By", "BiliRoamingX-Manager")
            putValue("SHA-256-Digest-Manifest", b64(mfDigest))
        }
        mf.entries.forEach { (k, v) ->
            sf.entries[k] = Attributes().apply { putValue("SHA-256-Digest", v.getValue("SHA-256-Digest")) }
        }
        val sfBytes = ByteArrayOutputStream().use { sf.write(it); it.toByteArray() }

        val sigGen = CMSSignedDataGenerator()
        sigGen.addCertificates(JcaCertStore(certList))
        val contentSigner = JcaContentSignerBuilder("SHA256WithRSA").build(keyPair.private)
        val digestCalc = JcaDigestCalculatorProviderBuilder().build()
        val signerInfoGen = JcaSignerInfoGeneratorBuilder(digestCalc).build(contentSigner, cert)
        sigGen.addSignerInfoGenerator(signerInfoGen)
        val signedData = sigGen.generate(CMSProcessableByteArray(sfBytes), true)
        val rsaBytes = signedData.encoded

        JarOutputStream(FileOutputStream(output)).use { out ->
            writeEntry(out, "META-INF/MANIFEST.MF", mfBytes)
            writeEntry(out, "META-INF/CERT.SF", sfBytes)
            writeEntry(out, "META-INF/CERT.RSA", rsaBytes)
            JarFile(input).use { jar ->
                jar.entries().asSequence().forEach { entry ->
                    if (entry.isDirectory || entry.name.startsWith("META-INF/")) return@forEach
                    out.putNextEntry(JarEntry(entry.name))
                    jar.getInputStream(entry).use { it.copyTo(out) }
                    out.closeEntry()
                }
            }
        }
    }

    private fun writeEntry(out: JarOutputStream, name: String, data: ByteArray) {
        out.putNextEntry(JarEntry(name))
        out.write(data)
        out.closeEntry()
    }

    private fun b64(bytes: ByteArray) = android.util.Base64.encodeToString(bytes, android.util.Base64.NO_WRAP)
}
