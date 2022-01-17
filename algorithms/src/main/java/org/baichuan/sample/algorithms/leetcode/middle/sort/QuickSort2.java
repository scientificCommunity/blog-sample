package org.baichuan.sample.algorithms.leetcode.middle.sort;

import java.util.Random;

/**
 * @author: tk (rivers.boat.snow@gmail.com)
 * @date: 2022/1/17
 * <p>
 * 快排解法2，相对解法1来讲，通过将基准值拿出来后留下的空位进行数组位置交换，在遍历数组需要交换数据位置时少了swap里的一个临时变量的赋值
 */
public class QuickSort2 extends SortHelper implements Sort {
    public static void main(String[] args) {
        for (int n : new QuickSort2().sortArray(new int[]{5, 2, 3, 1})) {
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
        int currRight = right;
        while (currLeft < currRight) {
            while (currLeft < currRight) {
                if (arr[currLeft] > pivot) {
                    //赋值前currRight是空位，可以直接赋值，赋值后因为currLeft的值已经被保存下来了，所以currLeft成了空位
                    // 这时就可以从右往左再继续找比基准值小的数来赋值到currLeft这个空位上
                    arr[currRight] = arr[currLeft];
                    //赋值完成后currRight对应的值肯定比基准值大，所以后面的循环从currRight左边一位依次往左找到第一个比基准值小的数
                    currRight--;
                    break;
                }
                currLeft++;
            }
            while (currLeft < currRight) {
                if (arr[currRight] < pivot) {
                    arr[currLeft] = arr[currRight];
                    currLeft++;
                    break;
                }
                currRight--;
            }
        }

        //把基准值放到准确的位置
        arr[currLeft] = pivot;

        //返回基准值应该在的位置
        return currLeft;
    }
}
