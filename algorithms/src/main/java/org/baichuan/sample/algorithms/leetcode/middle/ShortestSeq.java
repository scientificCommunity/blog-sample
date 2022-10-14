package org.baichuan.sample.algorithms.leetcode.middle;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: kuntang (rivers.boat.snow@gmail.com)
 * @date: 2022/4/17
 * 面试题 17.18. 最短超串
 * https://leetcode-cn.com/problems/shortest-supersequence-lcci/
 * 假设你有两个数组，一个长一个短，短的元素均不相同。找到长数组中包含短数组所有的元素的最短子数组，其出现顺序无关紧要。
 * <p>
 * 返回最短子数组的左端点和右端点，如有多个满足条件的子数组，返回左端点最小的一个。若不存在，返回空数组。
 * <p>
 * 示例 1:
 * <p>
 * 输入:
 * big = [7,5,9,0,2,1,3,5,7,9,1,1,5,8,8,9,7]
 * small = [1,5,9]
 * 输出: [7,10]
 * 示例 2:
 * <p>
 * 输入:
 * big = [1,2,3]
 * small = [4]
 * 输出: []
 * 提示：
 * <p>
 * big.length<= 100000
 * 1 <= small.length<= 100000
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/shortest-supersequence-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class ShortestSeq {
    public int[] shortestSeq(int[] big, int[] small) {
        if (big.length < small.length) {
            return new int[]{};
        }
        Map<Integer, Integer> map = new HashMap<>();
        for (int j : small) {
            if (map.containsKey(j)) {
                map.put(j, map.get(j) + 1);
            } else {
                map.put(j, 1);
            }
        }
        int minLen = big.length + 1;
        int ansLeft = 0;
        int ansRight = 0;

        int left = 0;
        int right = 0;
        //big子串中剩余应满足的字符数
        int diff = small.length;

        for (int i = 0; i < big.length; i++) {
            if (minLen == small.length) {
                break;
            }
            if (map.containsKey(big[i])) {
                map.put(big[i], map.get(big[i]) - 1);
                //如果对应的值不为负数，则表面这次匹配是不多余的，则令diff减1
                if (map.get(big[i]) >= 0) {
                    diff--;
                }
                if (diff == 0) {
                    int len = right - left;
                    if (len < minLen) {
                        minLen = len;
                        ansLeft = left;
                        ansRight = right;
                    }

                    for (int j = left; j < i; j++) {
                        //左指针右移
                        left++;
                        if (map.containsKey(big[j])) {
                            map.put(big[j], map.get(big[j]) + 1);
                            if (map.get(big[j]) > 0) {
                                diff++;
                                break;
                            }
                        }
                        len--;
                        if (len < minLen) {
                            minLen = len;
                            ansLeft = left;
                            ansRight = right;
                        }
                    }
                }
            }
            //右指针向右移动，应该放在循环的末尾，
            // 表示每次完整的循环（找到答案break时已经结束循环，指针就应该取当前值，而不应该再向右移）才会使指针向右移动（即下次循环的右指针所在的位置）
            right++;
        }
        if (ansLeft == 0 && ansRight == 0) {
            return new int[]{};
        }
        return new int[]{ansLeft, ansRight};
    }

    public static void main(String[] args) {
        //case 1
//        int[] big = new int[]{7, 5, 9, 0, 2, 1, 3, 5, 7, 9, 1, 1, 5, 8, 8, 9, 7};
//        int[] small = new int[]{1, 5, 9};

        int[] big = new int[]{1, 2, 3};
        int[] small = new int[]{1, 2, 3};

        int[] ints = new ShortestSeq().shortestSeq(big, small);
        System.out.println(ints);
    }
}
