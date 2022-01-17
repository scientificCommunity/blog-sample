package org.baichuan.sample.algorithms.leetcode.middle;

/**
 * @author: tk (rivers.boat.snow@gmail.com)
 * @date: 2022/1/14
 * 面试题 17.14. 最小K个数
 * https://leetcode-cn.com/problems/smallest-k-lcci/
 */
public class SmallestK_Solution2 {
    public static void main(String[] args) {

    }

    public int[] smallestK(int[] arr, int k) {
        int len = arr.length;
        int z = 0;
        int count = 0;
        for (int i = 0; i < len; i++) {
            int x = arr[i];
            for (int j = i + 1; j < len; j++) {
                if (x > arr[j]) {
                    count++;
                }
            }
        }
        return null;
    }
}
