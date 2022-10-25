package org.baichuan.sample.algorithms.leetcode.simple.interview

/**
 * 面试题 10.01. 合并排序的数组
 * https://leetcode.cn/problems/sorted-merge-lcci/
 */
class Merge {
    fun merge(A: IntArray, m: Int, B: IntArray, n: Int): Unit {
        var ma = m - 1
        var nb = n - 1
        var tailIndex = A.size - 1
        while (nb >= 0) {
            if (ma == -1) {
                B.copyInto(A, 0, 0, nb + 1)
                return
            }
            if (A[ma] > B[nb]) {
                A[tailIndex] = A[ma]
                ma--
            } else {
                A[tailIndex] = B[nb]
                nb--
            }
            tailIndex--
        }
    }
}

fun main() {
    Merge().merge(arrayOf(2, 0).toIntArray(), 1, arrayOf(1).toIntArray(), 1)
}