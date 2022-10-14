package org.baichuan.sample.algorithms.leetcode.middle;

import java.util.Arrays;

/**
 * @author: kuntang (rivers.boat.snow@gmail.com)
 * @date: 2022/4/16
 * 16. 最接近的三数之和
 * https://leetcode-cn.com/problems/3sum-closest/
 * 双指针法
 */
public class ThreeSumClosest {
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int result = nums[0] + nums[1] + nums[2];
        for (int i = 0; i < nums.length; i++) {
            int left = i + 1;
            int right = nums.length - 1;
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (Math.abs(sum - target) < Math.abs(target - result)) {
                    result = sum;
                }

                if (sum < target) {
                    left++;
                } else if (sum > target) {
                    right--;
                } else {
                    return target;
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        //case 1
//        int[] nums = new int[]{0, 2, 1, -3};
//        int target = 1;

        //case 2
        int[] nums = new int[]{-100, -98, -2, -1};
        int target = -101;
        int i = new ThreeSumClosest().threeSumClosest(nums, target);
        System.out.println(i);
    }
}
