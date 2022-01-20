package org.baichuan.sample.algorithms.leetcode.middle;

/**
 * @author: tk (rivers.boat.snow@gmail.com)
 * @date: 2022/1/20
 * 面试题 16.16. 部分排序
 * https://leetcode-cn.com/problems/sub-sort-lcci/
 * 思路（虽然题目没说数组是升序，但是解法是按照数组是升序来的）：
 * 可将数组分为左中右3部分，则存在：
 * 1. 左边的最大值小于中间的最小值
 * 2. 右边的最小值大于中间的最大值
 * 所以，从左到右历数组，记录下已遍历的数中的最大值tmpMax，如果array[i]比这个最大值小(不满足规律2)，
 * 则把中间部分的右端点last置为该数的下标i。否则将tmpMax置为array[i]。
 * 从右到左遍历数组，记录下已遍历的数中的最小值tmpMin，如果更左边的一个数array[i]比这个最小值大(不满足规律1),
 * 则更小中间部分的左端点first为这个数的下标i。否则将tmpMin置为array[i]。
 */
public class SubSort {
    public int[] subSort(int[] array) {
        int len = array.length;
        if (len == 0) {
            return new int[]{-1, -1};
        }
        int tmpMax = array[0];
        //中间部分的左端点坐标
        int first = -1;
        //中间部分的右端点坐标
        int last = -1;
        for (int i = 1; i < len; i++) {
            if (array[i] < tmpMax) {
                last = i;
            } else {
                tmpMax = array[i];
            }
        }
        //说明整个数组就是有序的
        if (last == -1) {
            return new int[]{first, last};
        }

        int tmpMin = array[len - 1];
        for (int i = last; i >= 0; i--) {
            if (array[i] > tmpMin) {
                first = i;
            } else {
                tmpMin = array[i];
            }
        }

        return new int[]{first, last};
    }
}
