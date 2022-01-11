package org.baichuan.sample.algorithms.leetcode.middle;

/**
 * @author: tk (rivers.boat.snow@gmail.com)
 * @date: 2022/1/11
 * https://leetcode-cn.com/problems/living-people-lcci/
 * 面试题 16.10. 生存人数
 */
public class MaxAliveYear {
    public static void main(String[] args) {
        int[] a = new int[]{1972, 1908, 1915, 1957, 1960, 1948, 1912, 1903, 1949, 1977, 1900, 1957, 1934, 1929, 1913, 1902, 1903, 1901};
        int[] b = new int[]{1997, 1932, 1963, 1997, 1983, 2000, 1926, 1962, 1955, 1997, 1998, 1989, 1992, 1975, 1940, 1903, 1983, 1969};
        System.out.println(new MaxAliveYear().maxAliveYear(a, b));
    }

    public int maxAliveYear(int[] birth, int[] death) {
        int[] changes = new int[102];
        for (int i = 0; i < birth.length; i++) {
            changes[birth[i] - 1900] += 1;
            changes[death[i] - 1899] -= 1;
        }

        int maxAlive = 0;
        int maxAliveYear = 1900;
        int currAlive = 0;
        for (int i = 0; i < 102; i++) {
            currAlive += changes[i];
            if (currAlive > maxAlive) {
                maxAlive = currAlive;
                maxAliveYear = i + 1900;
            }
        }

        return maxAliveYear;
    }
}
