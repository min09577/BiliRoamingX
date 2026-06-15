package app.revanced.biliroaming.manager

import java.io.File
import java.io.FileOutputStream
import java.io.ByteArrayOutputStream
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
import javax.security.auth.x500.X500Principal

/**
 * APK V1 JAR 签名器
 */
object ApkSigner {

    fun sign(input: File, output: File) {
        val keyGen = KeyPairGenerator.getInstance("RSA").apply { initialize(2048, SecureRandom()) }
        val keyPair = keyGen.generateKeyPair()

        val cert = createSelfSignedCert(keyPair)

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

        // 签名 SF
        val sig = Signature.getInstance("SHA256withRSA").apply { initSign(keyPair.private); update(sfBytes) }
        val sigBytes = sig.sign()

        // CERT.RSA = 简化 PKCS7: SEQUENCE { cert, sig }
        val rsaBytes = buildRsa(cert.encoded, sigBytes)

        // 写出
        JarOutputStream(FileOutputStream(output)).use { out ->
            writeJarEntry(out, "META-INF/MANIFEST.MF", mfBytes)
            writeJarEntry(out, "META-INF/CERT.SF", sfBytes)
            writeJarEntry(out, "META-INF/CERT.RSA", rsaBytes)

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

    private fun createSelfSignedCert(pair: java.security.KeyPair): X509Certificate {
        val now = Date()
        val expiry = Date(now.time + 365L * 86400000L * 10)

        @Suppress("DEPRECATION")
        val info = sun.security.x509.X509CertInfo().apply {
            set(sun.security.x509.X509CertInfo.VERSION, sun.security.x509.CertificateVersion(2))
            set(sun.security.x509.X509CertInfo.SERIAL_NUMBER, sun.security.x509.CertificateSerialNumber(BigInteger.valueOf(now.time)))
            set(sun.security.x509.X509CertInfo.ALGORITHM_ID, sun.security.x509.CertificateAlgorithmId(sun.security.x509.AlgorithmId.get("SHA256withRSA")))
            set(sun.security.x509.X509CertInfo.SUBJECT, X500Principal("CN=BiliRoamingX"))
            set(sun.security.x509.X509CertInfo.KEY, sun.security.x509.CertificateX509Key(pair.public))
            set(sun.security.x509.X509CertInfo.VALIDITY, sun.security.x509.CertificateValidity(now, expiry))
            set(sun.security.x509.X509CertInfo.ISSUER, X500Principal("CN=BiliRoamingX"))
        }
        val c = sun.security.x509.X509CertImpl(info)
        c.sign(pair.private, "SHA256withRSA")
        return c
    }

    private fun buildRsa(cert: ByteArray, sig: ByteArray): ByteArray {
        val out = ByteArrayOutputStream()
        val asn = ASN(out)

        asn.seq {
            asn.oid("1.2.840.113549.1.7.2")
            asn.tag(0xA0) {
                asn.seq {
                    asn.int(1)
                    asn.set { asn.seq { asn.oid("2.16.840.1.101.3.4.2.1"); asn.nullT() } }
                    asn.seq { asn.oid("1.2.840.113549.1.7.1") }
                    asn.tag(0xA0) { out.write(cert) }
                    asn.set {
                        asn.seq {
                            asn.int(1)
                            asn.seq {} // issuer/SN placeholder
                            asn.seq { asn.oid("2.16.840.1.101.3.4.2.1"); asn.nullT() }
                            asn.seq { asn.oid("1.2.840.113549.1.1.11"); asn.nullT() }
                            asn.bytes(0x04, sig)
                        }
                    }
                }
            }
        }
        return out.toByteArray()
    }

    private fun b64(bytes: ByteArray) = android.util.Base64.encodeToString(bytes, android.util.Base64.NO_WRAP)

    private fun writeJarEntry(out: JarOutputStream, name: String, data: ByteArray) {
        out.putNextEntry(JarEntry(name))
        out.write(data)
        out.closeEntry()
    }

    // Tiny ASN.1 DER builder
    private class ASN(val out: ByteArrayOutputStream) {
        fun seq(block: ASN.() -> Unit) = tlv(0x30, block)
        fun set(block: ASN.() -> Unit) = tlv(0x31, block)
        fun tag(tag: Int, block: ASN.() -> Unit) = tlv(tag, block)

        private fun tlv(tag: Int, block: ASN.() -> Unit) {
            out.write(tag)
            val inner = ByteArrayOutputStream()
            val asn = ASN(inner)
            block(asn)
            val data = inner.toByteArray()
            writeLen(out, data.size)
            out.write(data)
        }

        fun int(v: Int) {
            val bytes = when {
                v < 128 -> byteArrayOf(v.toByte())
                v < 256 -> byteArrayOf(v.toByte())
                v < 65536 -> byteArrayOf((v shr 8).toByte(), v.toByte())
                else -> byteArrayOf((v shr 24).toByte(), (v shr 16).toByte(), (v shr 8).toByte(), v.toByte())
            }
            bytes(0x02, bytes)
        }

        fun oid(s: String) {
            val parts = s.split(".").map { it.toInt() }
            val b = ByteArrayOutputStream()
            b.write(parts[0] * 40 + parts[1])
            for (i in 2 until parts.size) {
                var v = parts[i]
                if (v < 128) b.write(v)
                else {
                    val stack = mutableListOf(v and 0x7F)
                    v = v shr 7
                    while (v > 0) { stack.add(v and 0x7F); v = v shr 7 }
                    for (j in stack.size - 1 downTo 0) b.write(stack[j] or (if (j > 0) 0x80 else 0))
                }
            }
            bytes(0x06, b.toByteArray())
        }

        fun nullT() { out.write(0x05); out.write(0x00) }

        fun bytes(tag: Int, data: ByteArray) {
            out.write(tag)
            writeLen(out, data.size)
            out.write(data)
        }

        private fun writeLen(out: ByteArrayOutputStream, len: Int) {
            when {
                len < 128 -> out.write(len)
                len < 256 -> { out.write(0x81); out.write(len) }
                else -> { out.write(0x82); out.write(len shr 8); out.write(len and 0xFF) }
            }
        }
    }
}
