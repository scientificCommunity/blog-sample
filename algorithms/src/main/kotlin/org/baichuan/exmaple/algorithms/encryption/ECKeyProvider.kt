package org.baichuan.exmaple.algorithms.encryption

import java.io.File
import java.security.interfaces.ECPrivateKey
import java.security.interfaces.ECPublicKey

/**
 * @author: tk (rivers.boat.snow@gmail.com)
 * @date: 2021/10/26
 */
class ECKeyProvider() : BaseKeyProvider<ECPublicKey, ECPrivateKey>() {
    constructor(keyFile: File) : this() {
        this.keyFile = keyFile
    }

    constructor(keyString: String) : this() {
        this.keyString = keyString
    }

    override fun getPublicKeyById(keyId: String): ECPublicKey {
        TODO("Not yet implemented")
    }

    override fun getPrivateKey(): ECPrivateKey {
        return if (keyFile != null) {
            readPrivateKey(keyFile!!)
        } else {
            readPrivateKeyFromString(keyString!!)
        }
    }

    override fun getPrivateKeyId(): String {
        TODO("Not yet implemented")
    }

    override fun algorithms(): String {
        return "EC"
    }
}