package org.baichuan.sample.algorithms.leetcode.simple

import java.lang.StringBuilder

/**
 * 67. 二进制求和
 * https://leetcode.cn/problems/add-binary/
 */
class AddBinary {
    fun addBinary(a: String, b: String): String {
        val acs = a.toCharArray()
        val bcs = b.toCharArray()
        val result = StringBuilder()
        var overflowValue = 0
        for (i in 0 until acs.size.coerceAtLeast(bcs.size)) {
            val aIndex = acs.size - i - 1
            val bIndex = bcs.size - i - 1
            val aValue = if (aIndex > -1) acs[aIndex].toInt() - 48 else 0
            val bValue = if (bIndex > -1) bcs[bIndex].code - 48 else 0
            val sum = aValue + bValue + overflowValue
            if (sum > 1) {
                if (sum > 2) {
                    result.append('1')
                } else {
                    result.append('0')
                }
                overflowValue = 1
            } else {
                result.append(sum)
                overflowValue = 0
            }
        }
        if (overflowValue > 0) {
            result.append('1')
        }
        return result.reverse().toString()
    }
}

fun main() {
    AddBinary().addBinary("1010", "1011")
}