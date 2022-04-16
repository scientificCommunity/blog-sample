package org.baichuan.sample.algorithms.leetcode.middle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author: kuntang (rivers.boat.snow@gmail.com)
 * @date: 2022/4/16
 * 18. 四数之和
 * https://leetcode-cn.com/problems/4sum/
 */
public class FourSum {
    public List<List<Integer>> fourSum(int[] nums, long target) {
        List<List<Integer>> answer = new ArrayList<>();
        if (nums.length < 4) {
            return answer;
        }

        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            for (int j = i + 1; j < nums.length; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1]) {
                    continue;
                }
                int left = j + 1;
                int right = nums.length - 1;
                while (left < right) {
                    int sum = nums[i] + nums[j] + nums[left] + nums[right];
                    if (sum == target) {
                        answer.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));
                        while (left < right && nums[left] == nums[left + 1]) {
                            left++;
                        }
                        while (left < right && nums[right] == nums[right - 1]) {
                            right--;
                        }
                        left++;
                        right--;
                    } else if (sum < target) {
                        left++;
                    } else {
                        right--;
                    }
                }
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{0, 0, 0, -1000000000, -1000000000, -1000000000, -1000000000};
        List<List<Integer>> lists = new FourSum().fourSum(nums, -10000000000L);
        System.out.println(lists);
    }
}
