package org.baichuan.sample.algorithms.leetcode.simple

/**
 * 面试题 08.01. 三步问题
 * https://leetcode.cn/problems/three-steps-problem-lcci/
 */
class WaysToStep {
    fun waysToStep(n: Int): Int {
        val dp = LongArray(n + 3)
        dp[0] = 1
        dp[1] = 1
        dp[2] = 2
        for (i in 3..n) {
            dp[i] = (dp[i - 1] + dp[i - 2] + dp[i - 3]) % 1000000007
        }
        return dp[n].toInt()
    }
}