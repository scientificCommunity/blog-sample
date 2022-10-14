package org.baichuan.sample.algorithms.leetcode.simple;

/**
 * @author: tk (rivers.boat.snow@gmail.com)
 * @date: 2022/1/12
 * https://leetcode-cn.com/problems/diving-board-lcci/
 * 面试题 16.11. 跳水板
 */
public class DivingBoard {

    public int[] divingBoard(int shorter, int longer, int k) {
        if (k == 0) {
            return new int[0];
        }
        if (shorter == longer) {
            return new int[]{k * shorter};
        }
        int[] result = new int[k + 1];
        for (int i = 0; i < k + 1; i++) {
            result[i] = shorter * (k - i) + longer * i;
        }
        return result;
    }
}
