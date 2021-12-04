package org.baichuan.sample.algorithms.encryption

import org.bouncycastle.util.io.pem.PemReader
import java.io.File
import java.io.FileReader
import java.nio.charset.Charset
import java.nio.file.Files
import java.security.*
import java.security.spec.PKCS8EncodedKeySpec
import java.util.*


/**
 * @author: tk (rivers.boat.snow@gmail.com)
 * @date: 2021/10/26
 */
abstract class BaseKeyProvider<U : PublicKey, R : PrivateKey> : KeyProvider<U, R> {

    var keyFile: File? = null
    var keyString: String? = null

    @Throws(Exception::class)
    fun readPrivateKey(file: File): R {
        val factory = KeyFactory.getInstance(algorithms())
        FileReader(file).use { keyReader ->
            PemReader(keyReader).use { pemReader ->
                val pemObject = pemReader.readPemObject()
                val content = pemObject.content
                val privateKeySpec = PKCS8EncodedKeySpec(content)
                return factory.generatePrivate(privateKeySpec) as R
            }
        }
    }

    @Throws(Exception::class)
    fun readPublicKey(file: File): U {
        val factory = KeyFactory.getInstance(algorithms())
        FileReader(file).use { keyReader ->
            PemReader(keyReader).use { pemReader ->
                val pemObject = pemReader.readPemObject()
                val content = pemObject.content
                val privateKeySpec = PKCS8EncodedKeySpec(content)
                return factory.generatePublic(privateKeySpec) as U
            }
        }
    }

    @Throws(java.lang.Exception::class)
    fun readPrivateKeyFromFile(file: File): R {
        val key = String(Files.readAllBytes(file.toPath()), Charset.defaultCharset())
        return readPrivateKeyFromString(key)
    }

    @Throws(java.lang.Exception::class)
    fun readPublicKeyFromFile(file: File): U {
        val key = String(Files.readAllBytes(file.toPath()), Charset.defaultCharset())
        return readPublicKeyFromString(key)
    }

    /**
     * Note: 如果需要通过[KeyFactory]读取字符串并生成[PrivateKey]，需要保证字符串里面不存在Begin跟End以及换行符这类字符串
     */
    fun readPrivateKeyFromString(keyString: String): R {
        val privateKeyPEM = keyString
            .replace(PRIVATE_BEGIN, "")
            .replace(System.lineSeparator().toRegex(), "")
            .replace(PRIVATE_END, "")

        val decoded = Base64.getDecoder().decode(privateKeyPEM.toByteArray())
        val keyFactory = KeyFactory.getInstance(algorithms())
        val keySpec = PKCS8EncodedKeySpec(decoded)
        return keyFactory.generatePrivate(keySpec) as R
    }

    fun readPublicKeyFromString(keyString: String): U {
        val privateKeyPEM = keyString
            .replace(PUBLIC_BEGIN, "")
            .replace(System.lineSeparator().toRegex(), "")
            .replace(PUBLIC_END, "")

        val decoded = Base64.getDecoder().decode(privateKeyPEM.toByteArray())
        val keyFactory = KeyFactory.getInstance(algorithms())
        val keySpec = PKCS8EncodedKeySpec(decoded)
        return keyFactory.generatePublic(keySpec) as U
    }

    /**
     * 直接生成
     */
    fun generateRandom() {
        val keyGen = KeyPairGenerator.getInstance("EC")
        val random: SecureRandom = SecureRandom.getInstance("SHA1PRNG")

        keyGen.initialize(256, random)

        val pair = keyGen.generateKeyPair()
        val private = pair.private
        val pub = pair.public
    }

    abstract fun algorithms(): String

    companion object {
        const val PRIVATE_BEGIN = "-----BEGIN PRIVATE KEY-----"
        const val PRIVATE_END = "-----END PRIVATE KEY-----"

        const val PUBLIC_BEGIN = "-----BEGIN PUBLIC KEY-----"
        const val PUBLIC_END = "-----END PUBLIC KEY-----"
    }
}