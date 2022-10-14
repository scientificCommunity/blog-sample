package org.baichuan.sample.algorithms.leetcode.middle.sort;

import java.util.Random;

/**
 * @author: tk (rivers.boat.snow@gmail.com)
 * @date: 2022/1/20
 */
public class QuickSort3 extends ArrayHelper implements ArraySort {
    public static void main(String[] args) {
        for (int n : new QuickSort3().sortArray(new int[]{5, 2, 3, 1})) {
            System.out.println(n);
        }

    }

    @Override
    public int[] sortArray(int[] nums) {
        doSort(nums, 0, nums.length - 1);
        return nums;
    }

    public void doSort(int[] arr, int left, int right) {
        if (left < right) {
            int partition = partition(arr, left, right);
            doSort(arr, left, partition - 1);
            doSort(arr, partition + 1, right);
        }
    }

    public int partition(int[] arr, int left, int right) {
        int i = new Random().nextInt(right - left) + left;
        //基准值
        int pivot = arr[i];
        //把基准值跟最后一个值做交换，那么right就成了一个空位，可以用来直接赋值而不用再保存其值
        swap(arr, i, right);

        int currLeft = left;
        int currRight = right - 1;
        while (currLeft < currRight) {
            while (currLeft < currRight && arr[currLeft] <= pivot) {
                currLeft++;
            }

            while (currLeft < currRight && arr[currRight] >= pivot) {
                currRight--;
            }

            if (currLeft < currRight) {
                swap(arr, currLeft, currRight);
            }
        }

        if (arr[currLeft] < pivot) {
            currLeft++;
        }
        //把基准值放到准确的位置
        swap(arr, currLeft, right);

        //返回基准值应该在的位置
        return currLeft;
    }
}
