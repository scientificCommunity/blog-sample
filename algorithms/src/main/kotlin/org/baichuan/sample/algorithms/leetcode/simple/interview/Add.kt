package org.baichuan.sample.algorithms.leetcode.simple.interview

/**
 * 面试题 17.01. 不用加号的加法
 * https://leetcode.cn/problems/add-without-plus-lcci/
 */
class Add {
    fun add(a: Int, b: Int): Int {
        var m = a xor b //不进位加法

        var n = a and b shl 1 //进位,得出a xor b时哪些地方需要进位，左移后得出m需要在哪里继续进行不进位加法。
        // 后面再把m跟n作为原来的a跟b进行循环即可，直到最终的a and b为0时就说明全部加完了

        while (n != 0) {
            val temp = n xor m
            n = m and n shl 1
            m = temp
        }
        return m
    }
}

fun main() {
    println(10 xor 2)
}