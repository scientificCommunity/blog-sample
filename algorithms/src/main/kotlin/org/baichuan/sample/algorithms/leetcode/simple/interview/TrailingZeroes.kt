package org.baichuan.sample.algorithms.leetcode.simple.interview

/**
 * 面试题 16.05. 阶乘尾数
 * https://leetcode.cn/problems/factorial-zeros-lcci/
 */
class TrailingZeroes {
    /**
     * 有多少个尾随0,也就是说整个乘法过程中可以拆出多少个*10。也就是2*5。因为在阶乘中，5的数量要少于2的数量，也就是说，能拆出5必然能拆出2。
     * 所以，只要找出阶乘中因数5的数量就可以得出10的数量。也就是尾随0的数量
     *
     */
    fun trailingZeroes(n: Int): Int {
        var result = 0
        var nn = n
        while (nn >= 5) {
            nn /= 5
            result += nn
        }
        return result
    }
}