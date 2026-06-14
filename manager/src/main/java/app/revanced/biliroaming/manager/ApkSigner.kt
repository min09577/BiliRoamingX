package app.revanced.biliroaming.manager

import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.math.BigInteger
import java.security.KeyPairGenerator
import java.security.MessageDigest
import java.security.SecureRandom
import java.security.Security
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
import org.bouncycastle.cms.CMSSignedData
import org.bouncycastle.cms.CMSSignedDataGenerator
import org.bouncycastle.cms.jcajce.JcaSignerInfoGeneratorBuilder
import org.bouncycastle.jce.provider.BouncyCastleProvider
import org.bouncycastle.operator.jcajce.JcaContentSignerBuilder
import org.bouncycastle.operator.jcajce.JcaDigestCalculatorProviderBuilder
import org.bouncycastle.asn1.x500.X500Name
import org.bouncycastle.util.Store
import org.bouncycastle.cert.jcajce.JcaCertStore

/**
 * APK V1 签名器 - BouncyCastle 实现
 */
object ApkSigner {

    init {
        Security.removeProvider("BC")
        Security.addProvider(BouncyCastleProvider())
    }

    fun sign(input: File, output: File) {
        // 1. 生成密钥对
        val keyGen = KeyPairGenerator.getInstance("RSA", "BC")
        keyGen.initialize(2048, SecureRandom())
        val keyPair = keyGen.generateKeyPair()

        // 2. 生成自签名证书
        val issuer = X500Name("CN=BiliRoamingX")
        val subject = X500Name("CN=BiliRoamingX")
        val serial = BigInteger.valueOf(System.currentTimeMillis())
        val notBefore = Date(System.currentTimeMillis() - 86400000L)
        val notAfter = Date(System.currentTimeMillis() + 365L * 86400000L * 10)

        val certBuilder = JcaX509v3CertificateBuilder(
            issuer, serial, notBefore, notAfter, subject, keyPair.public
        )
        val certSigner = JcaContentSignerBuilder("SHA256WithRSA").setProvider("BC").build(keyPair.private)
        val cert = JcaX509CertificateConverter().getCertificate(certBuilder.build(certSigner))
        val certList = listOf<X509Certificate>(cert)

        // 3. 生成 MANIFEST.MF
        val manifest = Manifest()
        manifest.mainAttributes.putValue("Manifest-Version", "1.0")
        manifest.mainAttributes.putValue("Created-By", "BiliRoamingX-Manager")

        JarFile(input).use { jar ->
            jar.entries().asSequence().forEach { entry ->
                if (entry.isDirectory || entry.name.startsWith("META-INF/")) return@forEach
                val md = MessageDigest.getInstance("SHA-256")
                jar.getInputStream(entry).use { s ->
                    val buf = ByteArray(8192)
                    var len: Int
                    while (s.read(buf).also { len = it } != -1) md.update(buf, 0, len)
                }
                manifest.entries[entry.name] = Attributes().apply {
                    putValue("SHA-256-Digest", b64(md.digest()))
                }
            }
        }

        // 4. 写入 MANIFEST.MF
        val mfBytes = ByteArrayOutputStream().use { manifest.write(it); it.toByteArray() }

        // 5. 生成 CERT.SF
        val mfDigest = MessageDigest.getInstance("SHA-256").digest(mfBytes)
        val sf = Manifest()
        sf.mainAttributes.apply {
            putValue("Signature-Version", "1.0")
            putValue("Created-By", "BiliRoamingX-Manager")
            putValue("SHA-256-Digest-Manifest", b64(mfDigest))
        }
        manifest.entries.forEach { (name, attrs) ->
            sf.entries[name] = Attributes().apply {
                putValue("SHA-256-Digest", attrs.getValue("SHA-256-Digest"))
            }
        }
        val sfBytes = ByteArrayOutputStream().use { sf.write(it); it.toByteArray() }

        // 6. 生成 PKCS#7 签名 (CERT.RSA)
        val sigGen = CMSSignedDataGenerator()
        val certStore: Store<*> = JcaCertStore(certList)
        sigGen.addCertificates(certStore)

        val contentSigner = JcaContentSignerBuilder("SHA256WithRSA").setProvider("BC").build(keyPair.private)
        val digestCalcProvider = JcaDigestCalculatorProviderBuilder().setProvider("BC").build()
        val signerInfoGen = JcaSignerInfoGeneratorBuilder(digestCalcProvider).build(contentSigner, cert)
        sigGen.addSignerInfoGenerator(signerInfoGen)

        val signedData = sigGen.generate(CMSProcessableByteArray(sfBytes), true)
        val rsaBytes = signedData.encoded

        // 7. 写出签名后的 APK
        JarOutputStream(FileOutputStream(output)).use { out ->
            putEntry(out, "META-INF/MANIFEST.MF", mfBytes)
            putEntry(out, "META-INF/CERT.SF", sfBytes)
            putEntry(out, "META-INF/CERT.RSA", rsaBytes)

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

    private fun putEntry(out: JarOutputStream, name: String, data: ByteArray) {
        val entry = JarEntry(name)
        // META-INF 条目不需要压缩
        if (name.startsWith("META-INF/")) entry.method = JarEntry.STORED
        out.putNextEntry(entry)
        out.write(data)
        out.closeEntry()
    }

    private fun b64(bytes: ByteArray): String =
        android.util.Base64.encodeToString(bytes, android.util.Base64.NO_WRAP)
}
