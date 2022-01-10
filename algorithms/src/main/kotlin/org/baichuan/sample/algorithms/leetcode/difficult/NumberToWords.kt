package org.baichuan.sample.algorithms.leetcode.difficult

/**
 * @author: tk (rivers.boat.snow@gmail.com)
 * @date: 2022/1/10
 * https://leetcode-cn.com/problems/english-int-lcci/
 * 面试题 16.08. 整数的英语表示
 */
class NumberToWords {
    fun numberToWords(num: Int): String {
        val unit = arrayOf("Billion", "Million", "Thousand", "Hundred")
        val numberOfUnit = arrayOf(1000000000L, 1000000L, 1000L, 100L)
        val numberWord = arrayOf(
            "One",
            "Two",
            "Three",
            "Four",
            "Five",
            "Six",
            "Seven",
            "Eight",
            "Nine",
            "Ten",
            "Eleven",
            "Twelve",
            "Thirteen",
            "Fourteen",
            "Fifteen",
            "Sixteen",
            "Seventeen",
            "Eighteen",
            "Nineteen",
            "Twenty",
            "Thirty",
            "Forty",
            "Fifty",
            "Sixty",
            "Seventy",
            "Eighty",
            "Ninety"
        )
        val numberOfWord =
            arrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 30, 40, 50, 60, 70, 80, 90)

        /*var i = 0
        var result = ""
        var numVar = num
        while (i < number.size) {
            val l = numVar / number[i]
            if (l > 0) {
                result += findWord(l.toInt())
                numVar -= (l * number[i]).toInt()
            }
            i++
        }

        return result*/
        if (num == 0) {
            return "Zero"
        }
        val result = findResult(num, numberOfUnit, unit, numberOfWord)
        return result.substring(0, result.length - 1)
    }

    fun findResult(
        num: Int,
        numberOfUnit: Array<Long>,
        unit: Array<String>,
        numberOfWord: Array<Int>
    ): String {
        var i = 0
        var result = ""
        var numVar = num

        if (numVar >= 100) {
            while (i < numberOfUnit.size) {
                val l = numVar / numberOfUnit[i]
                if (l > 0) {
                    result += findResult(l.toInt(), numberOfUnit, unit, numberOfWord) + unit[i] + " "
                    numVar -= (l * numberOfUnit[i]).toInt()
                    if (numVar in 1..99) {
                        result += findResult(numVar, numberOfUnit, unit, numberOfWord)
                    }
                }
                i++
            }
        } else {
            var j = numberOfWord.size - 1
            while (j >= 0) {
                val l = numVar - numberOfWord[j]
                if (l >= 0) {
                    result += findWord(numberOfWord[j]) + findWord(l)
                    break
                } else {
                    j--
                }
            }
        }

        return result
    }

    fun findWord(num: Int): String {
        return when (num) {
            1 -> "One "
            2 -> "Two "
            3 -> "Three "
            4 -> "Four "
            5 -> "Five "
            6 -> "Six "
            7 -> "Seven "
            8 -> "Eight "
            9 -> "Nine "
            10 -> "Ten "
            11 -> "Eleven "
            12 -> "Twelve "
            13 -> "Thirteen "
            14 -> "Fourteen "
            15 -> "Fifteen "
            16 -> "Sixteen "
            17 -> "Seventeen "
            18 -> "Eighteen "
            19 -> "Nineteen "
            20 -> "Twenty "
            30 -> "Thirty "
            40 -> "Forty "
            50 -> "Fifty "
            60 -> "Sixty "
            70 -> "Seventy "
            80 -> "Eighty "
            90 -> "Ninety "
            else -> ""
        }
    }
}

fun main() {
    println(NumberToWords().numberToWords(1234567891))
}