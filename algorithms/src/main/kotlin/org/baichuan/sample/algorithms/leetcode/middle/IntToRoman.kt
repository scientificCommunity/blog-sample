package org.baichuan.sample.algorithms.leetcode.middle

/**
 * @author: tk (rivers.boat.snow@gmail.com)
 * @date: 2022/1/10
 * https://leetcode-cn.com/problems/integer-to-roman/
 * 12. 整数转罗马数字
 */
class IntToRoman {
    fun intToRoman(num: Int): String {
        val numArr = arrayOf(1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1)
        val romanArr = arrayOf("M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I")

        var result = ""
        var numVar = num
        var i = 0
        while (i < numArr.size) {
            if (numVar - numArr[i] >= 0) {
                result += romanArr[i]
                numVar -= numArr[i]
            } else {
                i++
            }
        }
        return result
    }

    private fun findRoman(num: Int): String {
        return when (num) {
            1000 -> "M"
            900 -> "CM"
            500 -> "D"
            400 -> "CD"
            100 -> "C"
            90 -> "XC"
            50 -> "L"
            40 -> "XL"
            10 -> "X"
            9 -> "IX"
            5 -> "V"
            4 -> "IV"
            else -> "I"
        }
    }
}