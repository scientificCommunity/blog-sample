package org.baichuan.sample.algorithms.leetcode.simple

/**
 * @author: tk (rivers.boat.snow@gmail.com)
 * @date: 2022/1/10
 * https://leetcode-cn.com/problems/roman-to-integer/
 * 13. 罗马数字转整数
 */
class RomanToInt {
    fun romanToInt(s: String): Int {
        var result = 0
        val chars = s.toCharArray()
        var i = 0
        while (i < chars.size) {
            if (findDigits(chars[i]) < findDigits(chars[i + 1])) {
                result += findDigits(chars[i + 1]) - findDigits(chars[i])
                i += 2
            } else {
                result += findDigits(chars[i])
                i++
            }

            if (i == chars.size - 1) {
                return result + findDigits(chars[i])
            }
        }
        return result
    }

    private fun findDigits(c: Char): Int {
        return when (c) {
            'M' -> 1000
            'D' -> 500
            'C' -> 100
            'L' -> 50
            'X' -> 10
            'V' -> 5
            else -> 1
        }
    }
}