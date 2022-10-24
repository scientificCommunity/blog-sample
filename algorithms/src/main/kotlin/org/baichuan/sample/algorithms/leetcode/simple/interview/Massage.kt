package org.baichuan.sample.algorithms.leetcode.simple.interview

/**
 * https://leetcode.cn/problems/the-masseuse-lcci/
 * 面试题 17.16. 按摩师
 */
class Massage {
    /**
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     * 状态转移方程： dp[i] = max(dp[i-1],dp[i-2])
     */
    fun massage(nums: IntArray): Int {
        if (nums.isEmpty()) {
            return 0
        }
        if (nums.size == 1) {
            return nums[0]
        }
        val dp = IntArray(nums.size)
        dp[0] = nums[0]
        dp[1] = Math.max(nums[0], nums[1])
        for (i in 2 until nums.size) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i])
        }
        return dp[nums.size - 1]
    }

    /**
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     * 状态转移方程： dp[i] = max(dp[i-1],dp[i-2])
     * 从递推关系来看，每次计算时需要保存的状态只有3个：dp[i] dp[i-1] dp[i-2]，所以可以考虑每次循环可以丢弃i-3
     * 所以只需要保证每次循环，i i-1 i-2这3个连续的数对应的结果在dp里，并且能通过下表找到对应的值即可。而dp本身只有0,1,2几个空位。
     * 而x%3 = (x-3)%3。而且在i连续的情况下取模产生的数也是连续的。所以，dp[i%3] = max(dp[(i-1)%3],dp[(i-2)%3])
     */
    fun massage1(nums: IntArray): Int {
        if (nums.isEmpty()) {
            return 0
        }
        if (nums.size == 1) {
            return nums[0]
        }
        val dp = IntArray(3)
        dp[0] = nums[0]
        dp[1] = Math.max(nums[0], nums[1])
        for (i in 2 until nums.size) {
            dp[i % 3] = Math.max(dp[(i - 1) % 3], dp[(i - 2) % 3] + nums[i])
        }
        return dp[(nums.size - 1) % 3]
    }
}

fun main() {

}