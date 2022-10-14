package org.baichuan.sample.algorithms.leetcode.simple;

/**
 * @author: tk (rivers.boat.snow@gmail.com)
 * @date: 2022/1/21
 * 面试题 16.17. 连续数列
 * https://leetcode-cn.com/problems/contiguous-sequence-lcci/
 *
 * <p>
 * 给定一个整数数组，找出总和最大的连续数列，并返回总和。
 * <p>
 * 示例：
 * <p>
 * 输入： [-2,1,-3,4,-1,2,1,-5,4]
 * 输出： 6
 * 解释： 连续子数组 [4,-1,2,1] 的和最大，为 6。
 * <p>
 * <p>
 * 解法：动态规划
 * 核心思路：
 * 1. 把以数组中每一项结尾的且和最大的连续子序列的和求出来
 * 2. 再从这些项中求最大值，即可得到最大值。
 * 第一步有一个递推关系存在：f(i) = max{ f(i-1) + nums[i], nums[i] }，
 * 所以，遍历时记录下f(i)的值来用作后续的递推，以及到i为止所有f(i)的最大值，即可得出最终结果
 */
public class MaxSubArray {
    public static void main(String[] args) {
        System.out.println(new MaxSubArray().maxSubArray(new int[]{1, -2, 2, -10, 2, 2}));
    }

    public int maxSubArray(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        //以i结尾的总和最大的连续数列的和
        int pre = nums[0];
        //结果
        int currMax = nums[0];
        for (int i = 1; i < nums.length; i++) {
            pre = Math.max(pre + nums[i], nums[i]);
            currMax = Math.max(pre, currMax);
        }
        return currMax;
    }

    public int maxSubArray2(int[] nums) {
        int pre = 0, maxAns = nums[0];
        for (int x : nums) {
            pre = Math.max(pre + x, x);
            maxAns = Math.max(maxAns, pre);
        }
        return maxAns;
    }
}
