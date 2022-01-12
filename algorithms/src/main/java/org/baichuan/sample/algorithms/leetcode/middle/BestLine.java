package org.baichuan.sample.algorithms.leetcode.middle;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: tk (rivers.boat.snow@gmail.com)
 * @date: 2022/1/12
 * https://leetcode-cn.com/problems/best-line-lcci/
 * 面试题 16.14. 最佳直线
 */
public class BestLine {
    /**
     * 1. 不平行的直线与直线之间最多只有一个交点 ——> 一条直线上的点a与非这条直线上的点b的连线不可能经过在这条直线上除点a以外的点
     * 2. 存在的这条直线最多覆盖数量为points.length的点
     *
     * @param points
     * @return
     */
    public int[] bestLine(int[][] points) {
        int[] result = new int[2];
        int max = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < points.length; i++) {

            for (int j = i + 1; j < points.length; j++) {
                int dx = points[i][0] - points[j][0];
                int dy = points[i][1] - points[j][1];
                int count = 0;
                for (int k = j + 1; k < points.length; k++) {
                    int dx1 = points[j][0] - points[i][0];
                    int dy1 = points[j][0] - points[i][0];

                    if (dy1 * dx == dy * dx1) {
                        count++;
                    }
                    if (count > max) {
                        result[0] = i;
                        result[1] = j;
                    }
                }

            }
        }
        return result;
    }
}
