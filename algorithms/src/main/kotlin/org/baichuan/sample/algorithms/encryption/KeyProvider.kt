package org.baichuan.sample.algorithms.encryption

import java.security.PrivateKey
import java.security.PublicKey

/**
 * @author: tk (rivers.boat.snow@gmail.com)
 * @date: 2021/10/26
 */
interface KeyProvider<U : PublicKey, R : PrivateKey> {
    /**
     * Getter for the Public Key instance with the given Id. Used to verify the signature on the JWT verification stage.
     *
     * @param keyId the Key Id specified in the Token's Header or null if none is available. Provides a hint on which Public Key to use to verify the token's signature.
     * @return the Public Key instance
     */
    fun getPublicKeyById(keyId: String): U

    /**
     * Getter for the Private Key instance. Used to sign the content on the JWT signing stage.
     *
     * @return the Private Key instance
     */
    fun getPrivateKey(): R

    /**
     * Getter for the Id of the Private Key used to sign the tokens. This represents the `kid` claim and will be placed in the Header.
     *
     * @return the Key Id that identifies the Private Key or null if it's not specified.
     */
    fun getPrivateKeyId(): String
}