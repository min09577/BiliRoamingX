package app.revanced.biliroaming.manager

import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.math.BigInteger
import java.security.KeyPairGenerator
import java.security.MessageDigest
import java.security.SecureRandom
import java.util.Date
import java.util.jar.Attributes
import java.util.jar.JarEntry
import java.util.jar.JarFile
import java.util.jar.JarOutputStream
import java.util.jar.Manifest
import org.bouncycastle.asn1.x500.X500Name
import org.bouncycastle.cert.jcajce.JcaX509CertificateConverter
import org.bouncycastle.cert.jcajce.JcaX509v3CertificateBuilder
import org.bouncycastle.cert.jcajce.JcaCertStore
import org.bouncycastle.cms.CMSProcessableByteArray
import org.bouncycastle.cms.CMSSignedDataGenerator
import org.bouncycastle.cms.jcajce.JcaSignerInfoGeneratorBuilder
import org.bouncycastle.jce.provider.BouncyCastleProvider
import org.bouncycastle.operator.jcajce.JcaContentSignerBuilder
import org.bouncycastle.operator.jcajce.JcaDigestCalculatorProviderBuilder
import java.security.Security
import java.security.cert.X509Certificate

object ApkSigner {

    private const val TAG = "BiliRoamingX-Mgr"

    init {
        try {
            if (Security.getProvider("BC") == null) {
                Security.addProvider(BouncyCastleProvider())
                android.util.Log.i(TAG, "BouncyCastleProvider 注册成功")
            }
        } catch (e: Exception) {
            android.util.Log.e(TAG, "BouncyCastleProvider 注册失败", e)
        }
    }

    fun sign(input: File, output: File) {
        android.util.Log.i(TAG, "开始签名: ${input.length()} bytes")
        val keyPair = try {
            KeyPairGenerator.getInstance("RSA").apply {
                initialize(2048, SecureRandom())
            }.generateKeyPair()
        } catch (e: Exception) {
            throw IllegalStateException("生成密钥对失败: ${e.message}", e)
        }

        val issuer = X500Name("CN=BiliRoamingX Manager")
        val subject = X500Name("CN=BiliRoamingX")
        val serial = BigInteger.valueOf(System.currentTimeMillis())
        val notBefore = Date(System.currentTimeMillis() - 86400000L)
        val notAfter = Date(System.currentTimeMillis() + 365L * 86400000L * 10)

        val certBuilder = JcaX509v3CertificateBuilder(issuer, serial, notBefore, notAfter, subject, keyPair.public)
        val contentSigner = JcaContentSignerBuilder("SHA256WithRSA").build(keyPair.private)
        val cert = JcaX509CertificateConverter().getCertificate(certBuilder.build(contentSigner))

        // MANIFEST.MF
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
                mf.entries[entry.name] = Attributes().apply {
                    putValue("SHA-256-Digest", b64(md.digest()))
                }
            }
        }
        val mfBytes = ByteArrayOutputStream().use { mf.write(it); it.toByteArray() }

        // CERT.SF
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

        // CERT.RSA via CMS SignedData
        val sigGen = CMSSignedDataGenerator()
        sigGen.addCertificates(JcaCertStore(listOf(cert as X509Certificate)))
        val signer = JcaContentSignerBuilder("SHA256WithRSA").setProvider("BC").build(keyPair.private)
        val digestCalc = JcaDigestCalculatorProviderBuilder().setProvider("BC").build()
        val signerInfoGen = JcaSignerInfoGeneratorBuilder(digestCalc).build(signer, cert)
        sigGen.addSignerInfoGenerator(signerInfoGen)
        val rsaBytes = sigGen.generate(CMSProcessableByteArray(sfBytes), true).encoded

        // 写出
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
