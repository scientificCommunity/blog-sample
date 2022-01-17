package org.baichuan.sample.algorithms.leetcode.middle.sort;

/**
 * @author: tk (rivers.boat.snow@gmail.com)
 * @date: 2022/1/17
 */
public class SortHelper {
    public void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
