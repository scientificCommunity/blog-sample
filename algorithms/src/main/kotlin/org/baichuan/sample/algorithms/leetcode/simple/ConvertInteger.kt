package org.baichuan.sample.algorithms.leetcode.simple

class ConvertInteger {
    fun convertInteger(A: Int, B: Int): Int {
        var x = A xor B  //不同的位变为1
        var result = 0
        while (x != 0) {
            x = x and (x - 1) //每次减少一个1
            result++
        }
        return result
    }
}