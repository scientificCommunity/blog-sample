package org.baichuan.sample.algorithms.leetcode.utils;

/**
 * @author: tk (rivers.boat.snow@gmail.com)
 * @date: 2022/1/19
 */
public class ArrayUtils {
    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
