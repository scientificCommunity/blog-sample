package org.baichuan.sample.algorithms.leetcode.middle.sort;

import java.util.Random;

/**
 * @author: tk (rivers.boat.snow@gmail.com)
 * @date: 2022/1/17
 * 快排性能比较高的精髓是：x>=y ==》 x比所有比y小的数大
 * 所以每一次通过基准值进行partition后如果能够更均匀的切分数组，就能获得更高的性能(减少了比较次数，即基准值两边的数的大小的比较)。
 * <p>
 * 所以基准值的选择很重要，尽量保证能够更均匀的切分数组。但是这个保证很难通过一个固定的基准值来实现，因为我们不知道原数组是什么样的，
 * 很有可能我们这个固定值刚好每次切分出来的分区一边只有一个元素，另一边是剩下的元素，那么在这种情况下，就不能用到上面所说的"精髓"所带来的优势(比较次数减少)。
 * 这时，每两个数之间都会发生大小比较，算法的时间复杂度上升至n^2。 比如：数组元素本身有序，基准值每次都取最左边或者最右边的元素。
 * <p>
 * 可以选择随机数作为基准值的下标来尽量避免极端情况的出现。
 */
public class QuickSort extends ArrayHelper implements ArraySort {
    public static void main(String[] args) {
        for (int n : new QuickSort().sortArray(new int[]{5, 2, 3, 1})) {
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
        /*
          随机数作为基准值的下标,相对题目的case来说，这里计算随机数是一个比较大的性能损耗
          如果不用随机数作为基准值的下标，可以考虑用(right-left)/2 + left，这样的话leetcode上的性能分会有一个很大的提升。
          但是尽量不能用left或者right作为基准值的下标, 因为这种情况下如果数组本身就是有序的(升序或者降序)，
          这样会导致partition起不到分割数组的作用(因为后续划分出来的区间肯定有一个不满足left<right)
         */
        int i = new Random().nextInt(right - left) + left;
        //基准值
        int pivot = arr[i];
        //把基准值跟最后一个值做交换，目的是在循环里不添加额外判断条件的情况下保证基准值本身就不用比较
        swap(arr, i, right);

        /*
          表示在基准值左边的数的个数+left(因为left左边的所有数从一开始就是小于基准值的)。每有一个数小于基准值，就让该值加1
          同时将该值在数组中的位置j跟c对应的位置的值进行交换。那么最终c的值就是基准值在数组中最终的位置(左边的比基准值小，右边的比基准值大)
          如果数组本身就是有序的，那么这些交换(从left到基准值最终下标)其实就是自己跟自己做交换。这些是无效交换(损耗不是很大)。
         */
        int c = left;
        //跳过right-1的比较
        for (int j = left; j < right; j++) {
            if (arr[j] <= pivot) {
                swap(arr, c, j);
                c++;
            }
        }

        //把基准值放到准确的位置
        swap(arr, c, right);

        //返回基准值应该在的位置
        return c;
    }
}
