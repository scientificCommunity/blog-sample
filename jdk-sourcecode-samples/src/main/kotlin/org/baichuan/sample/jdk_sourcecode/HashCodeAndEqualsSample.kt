package org.baichuan.sample.jdk_sourcecode

/**
 * @author: tk (soulmate.tangk at gmail dot com)
 * @date: 2021/11/25
 */
class HashCodeAndEqualsSample {
}

fun main() {
    println("${'a'.hashCode()} - ${97.hashCode()} - ${"a".hashCode()} - ${"哈哈".hashCode()}")
}