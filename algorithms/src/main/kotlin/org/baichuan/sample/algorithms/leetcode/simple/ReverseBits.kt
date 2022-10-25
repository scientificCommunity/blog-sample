package org.baichuan.sample.algorithms.leetcode.simple

/**
 * 面试题 05.03. 翻转数位
 * https://leetcode.cn/problems/reverse-bits-lcci/
 */
class ReverseBits {
    fun reverseBits(num: Int): Int {
        var i1 = 0
        var i2 = 0
        var res = 0
        for (i in 0 until 32) {
            if ((num and (1 shl i)) != 0) {
                i1++
                i2++
            } else {
                //中间断开，则i2等于断开前那一段加1，如果后面连上了，则继续增加。如果下一个也是0，则i2变为1
                //i1的作用是记录下断开前那一段的长度。这样就可以得到任何两段不相连的111组合后的长度
                i2 = i1 + 1
                i1 = 0
            }
            res = Math.max(res, i2)
        }
        return res
    }
}

fun main() {
    ReverseBits().reverseBits(768374)
}