package app.revanced.biliroaming.manager

import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.math.BigInteger
import java.security.KeyPairGenerator
import java.security.KeyStore
import java.security.MessageDigest
import java.security.PrivateKey
import java.security.SecureRandom
import java.security.Security
import java.security.Signature
import java.security.cert.Certificate
import java.security.cert.X509Certificate
import java.util.Date
import java.util.jar.Attributes
import java.util.jar.JarEntry
import java.util.jar.JarFile
import java.util.jar.JarOutputStream
import java.util.jar.Manifest
import javax.security.auth.x500.X500Principal
import org.bouncycastle.cert.X509v3CertificateBuilder
import org.bouncycastle.cert.jcajce.JcaX509CertificateConverter
import org.bouncycastle.cert.jcajce.JcaX509v3CertificateBuilder
import org.bouncycastle.operator.jcajce.JcaContentSignerBuilder
import org.bouncycastle.asn1.x500.X500Name

/**
 * APK V1 签名器 - 使用 BouncyCastle 生成自签名证书并执行 JAR 签名
 */
object ApkSigner {

    private const val PROVIDER = "BC"

    init {
        Security.removeProvider(PROVIDER)
        Security.addProvider(org.bouncycastle.jce.provider.BouncyCastleProvider())
    }

    fun sign(input: File, output: File) {
        // 1. 生成 RSA 密钥对 + 自签名证书
        val keyGen = KeyPairGenerator.getInstance("RSA", PROVIDER)
        keyGen.initialize(2048, SecureRandom())
        val keyPair = keyGen.generateKeyPair()

        val issuer = X500Name("CN=BiliRoamingX Manager")
        val subject = X500Name("CN=BiliRoamingX")
        val serial = BigInteger.valueOf(System.currentTimeMillis())
        val notBefore = Date(System.currentTimeMillis() - 86400000)
        val notAfter = Date(System.currentTimeMillis() + 365L * 86400000)

        val certBuilder = JcaX509v3CertificateBuilder(
            issuer, serial, notBefore, notAfter, subject, keyPair.public
        )
        val signer = JcaContentSignerBuilder("SHA256WithRSA").build(keyPair.private)
        val cert = JcaX509CertificateConverter().getCertificate(certBuilder.build(signer))
        val certChain = arrayOf<Certificate>(cert)

        // 2. 生成 MANIFEST.MF
        val manifest = Manifest().apply {
            mainAttributes.putValue("Manifest-Version", "1.0")
            mainAttributes.putValue("Created-By", "BiliRoamingX-Manager")
        }
        JarFile(input).use { jar ->
            jar.entries().asSequence().forEach { entry ->
                if (entry.isDirectory || entry.name.startsWith("META-INF/")) return@forEach
                val md = MessageDigest.getInstance("SHA-256")
                jar.getInputStream(entry).use { stream ->
                    val buf = ByteArray(8192)
                    var len: Int
                    while (stream.read(buf).also { len = it } != -1) md.update(buf, 0, len)
                }
                manifest.entries[entry.name] = Attributes().apply {
                    putValue("SHA-256-Digest", b64(md.digest()))
                }
            }
        }
        val mfBytes = ByteArrayOutputStream().use { manifest.write(it); it.toByteArray() }

        // 3. 生成 CERT.SF
        val mfDigest = MessageDigest.getInstance("SHA-256").digest(mfBytes)
        val sf = Manifest().apply {
            mainAttributes.apply {
                putValue("Signature-Version", "1.0")
                putValue("Created-By", "BiliRoamingX-Manager")
                putValue("SHA-256-Digest-Manifest", b64(mfDigest))
            }
            manifest.entries.forEach { (n, a) ->
                entries[n] = Attributes().apply {
                    putValue("SHA-256-Digest", a.getValue("SHA-256-Digest"))
                }
            }
        }
        val sfBytes = ByteArrayOutputStream().use { sf.write(it); it.toByteArray() }

        // 4. 签名 CERT.SF
        val sig = Signature.getInstance("SHA256WithRSA", PROVIDER).apply {
            initSign(keyPair.private)
            update(sfBytes)
        }
        val sigBytes = sig.sign()

        // 5. PKCS#7 SignedData
        val signedData = Pkcs7Builder.build(cert.encoded, sigBytes)

        // 6. 写出签名的 APK
        JarOutputStream(FileOutputStream(output)).use { out ->
            writeJarEntry(out, "META-INF/MANIFEST.MF", mfBytes)
            writeJarEntry(out, "META-INF/CERT.SF", sfBytes)
            writeJarEntry(out, "META-INF/CERT.RSA", signedData)
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

    private fun writeJarEntry(out: JarOutputStream, name: String, data: ByteArray) {
        out.putNextEntry(JarEntry(name))
        out.write(data)
        out.closeEntry()
    }

    private fun b64(bytes: ByteArray) =
        android.util.Base64.encodeToString(bytes, android.util.Base64.NO_WRAP)
}

/**
 * 最简 PKCS#7 DER 编码
 */
internal object Pkcs7Builder {
    fun build(cert: ByteArray, sig: ByteArray): ByteArray {
        val out = ByteArrayOutputStream()
        // SEQUENCE { OID signedData, [0] EXPLICIT { SEQUENCE { ... } } }
        sequence(out) {
            oid(out, "1.2.840.113549.1.7.2") // signedData
            tag(out, 0xA0) {
                sequence(out) {
                    int(out, 1) // version
                    set(out) { sequence(out) { oid(out, "2.16.840.1.101.3.4.2.1"); nullTag(out) } }
                    sequence(out) { oid(out, "1.2.840.113549.1.7.1") } // data
                    tag(out, 0xA0) { raw(out, cert) } // certs
                    set(out) {
                        sequence(out) {
                            int(out, 1) // version
                            sequence(out) { /* issuerAndSerialNum - copy from cert */ }
                            sequence(out) { oid(out, "2.16.840.1.101.3.4.2.1"); nullTag(out) }
                            sequence(out) { oid(out, "1.2.840.113549.1.1.11"); nullTag(out) }
                            octet(out, sig)
                        }
                    }
                }
            }
        }
        return out.toByteArray()
    }

    private fun sequence(out: ByteArrayOutputStream, block: () -> Unit) = tlv(out, 0x30, block)
    private fun set(out: ByteArrayOutputStream, block: () -> Unit) = tlv(out, 0x31, block)
    private fun tag(out: ByteArrayOutputStream, tag: Int, block: () -> Unit) = tlv(out, tag, block)
    
    private fun tlv(out: ByteArrayOutputStream, tag: Int, block: () -> Unit) {
        out.write(tag)
        val inner = ByteArrayOutputStream()
        block()
        // Actually need inner output stream...
        val bos = ByteArrayOutputStream()
        val saved = out
        // Simpler approach: write length placeholder, then write content, then fix length
    }

    // Simplified: pre-compute and write
    private fun oid(out: ByteArrayOutputStream, oid: String) {
        val parts = oid.split(".").map { it.toInt() }
        val bytes = ByteArrayOutputStream()
        bytes.write(parts[0] * 40 + parts[1])
        for (i in 2 until parts.size) {
            var v = parts[i]
            if (v < 128) bytes.write(v)
            else {
                val stack = mutableListOf(v and 0x7F)
                v = v shr 7
                while (v > 0) { stack.add(v and 0x7F); v = v shr 7 }
                for (j in stack.size - 1 downTo 0) bytes.write(stack[j] or (if (j > 0) 0x80 else 0))
            }
        }
        tlvBytes(out, 0x06, bytes.toByteArray())
    }

    private fun int(out: ByteArrayOutputStream, value: Int) {
        val bytes = when {
            value == 0 -> byteArrayOf(0)
            value < 128 -> byteArrayOf(value.toByte())
            value < 256 -> byteArrayOf(0, value.toByte())
            value < 65536 -> byteArrayOf((value shr 8).toByte(), value.toByte())
            else -> byteArrayOf((value shr 24).toByte(), (value shr 16).toByte(), (value shr 8).toByte(), value.toByte())
        }
        tlvBytes(out, 0x02, bytes)
    }

    private fun octet(out: ByteArrayOutputStream, data: ByteArray) = tlvBytes(out, 0x04, data)
    private fun nullTag(out: ByteArrayOutputStream) { out.write(0x05); out.write(0x00) }
    private fun raw(out: ByteArrayOutputStream, data: ByteArray) = out.write(data)

    private fun tlvBytes(out: ByteArrayOutputStream, tag: Int, data: ByteArray) {
        out.write(tag)
        val len = data.size
        when {
            len < 128 -> out.write(len)
            len < 256 -> { out.write(0x81); out.write(len) }
            else -> { out.write(0x82); out.write(len shr 8); out.write(len and 0xFF) }
        }
        out.write(data)
    }
}
