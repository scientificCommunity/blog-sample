package org.baichuan.sample.algorithms.leetcode.middle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author: kuntang (rivers.boat.snow@gmail.com)
 * @date: 2022/4/22
 * 面试题 17.08. 马戏团人塔
 * https://leetcode-cn.com/problems/circus-tower-lcci/
 */
public class BestSeqAtIndex {
    public int bestSeqAtIndex(int[] height, int[] weight) {
        int len = height.length;
        int[][] person = new int[len][2];
        for (int i = 0; i < len; ++i)
            person[i] = new int[]{height[i], weight[i]};
        //身高升序，升高相同则体重升序（目的是为了后续找体重里的最长递增子序列时在身高相同的数据不会成为体重递增，从而保证最终结果在两个维度严格递增）。
        Arrays.sort(person, (a, b) -> a[0] == b[0] ? b[1] - a[1] : a[0] - b[0]);
        int[] dp = new int[len];
        int res = 0;
        for (int[] pair : person) {
            int i = Arrays.binarySearch(dp, 0, res, pair[1]);
            //pair[1]不再dp中
            if (i < 0) {
                //设置i为插入点
                i = -(i + 1);
                //插入pair[1]到对应插入点
                dp[i] = pair[1];
            }
            //因为i表示pair[1]在dp中的位置，保证res值增加的连续性意味着当i==res时，原数组在index<i时必然有res个数小于i对应的值，也就是n=i时的最长递增子序列
            if (i == res)
                ++res;
        }
        return res;
    }

    //先按身高排序，再对胖瘦求最长递增子序列。先遍历各种可能的递增分支求出最长子序列的下标，再计算长度。
    //该方案超时
    public int bestSeqAtIndex2(int[] height, int[] weight) {
        int len = height.length;
        int[][] person = new int[len][2];
        for (int i = 0; i < len; ++i)
            person[i] = new int[]{height[i], weight[i]};
        //身高升序，升高相同则体重升序（目的是为了后续找体重里的最长递增子序列时在身高相同的数据不会成为体重递增，从而保证最终结果在两个维度严格递增）。
        Arrays.sort(person, (a, b) -> a[0] == b[0] ? b[1] - a[1] : a[0] - b[0]);
        ArrayList<Integer> ans = new ArrayList<>();
        ans.add(0);
        return find(person, 1, ans);
    }

    public int find(int[][] person, int beginIndex, List<Integer> ans) {
        int ans1 = 0;
        int preIndex = beginIndex - 1;
        for (int i = beginIndex; i < person.length; i++) {
            if (person[i][1] <= person[preIndex][1]) {
                List<Integer> ans2 = new ArrayList<>(ans);
                int removeCount = 0;
                for (int j : ans2) {
                    //如果之前的递增序列的最后一个元素小于当前值，则认为当前值可以与前面的递增序列构成一个新的递增序列，退出遍历进入下一个递增序列分支
                    if (person[j][1] < person[i][1]) {
                        //不必更新ans2，person[i]替换了person[preIndex2]的位置，同时j没有再减少
                        //ans2++;
                        break;
                    } else {
                        //统计需要移除的元素数
                        removeCount++;
                    }
                }
                if (removeCount > 0) {
                    ans2.subList(0, removeCount).clear();
                }

                //本轮循环的值肯定在这个分支的序列里
                ans2.add(0, i);
                ans1 = Math.max(ans1, find(person, i + 1, ans2));
            } else {
                preIndex = i;
                ans.add(0, i);
            }
        }
        return Math.max(ans.size(), ans1);
    }

    public static void main(String[] args) {
        //case 1
//        int[] height = new int[]{2868, 5485, 1356, 1306, 6017, 8941, 7535, 4941, 6331, 6181};
//        int[] weight = new int[]{5042, 3995, 7985, 1651, 5991, 7036, 9391, 428, 7561, 8594};
//        System.out.println(new BestSeqAtIndex().bestSeqAtIndex2(height, weight));

        //case 2
//        int[] height = new int[]{5401, 9628, 3367, 6600, 6983, 7853, 5715, 2654, 4453, 8619};
//        int[] weight = new int[]{3614, 1553, 2731, 7894, 8689, 182, 7632, 4465, 8932, 4304};
//        System.out.println(new BestSeqAtIndex().bestSeqAtIndex2(height, weight));

        //case 3
//        int[] height = new int[]{2833, 9393, 8309, 8764, 3013, 4651, 7768, 682, 3315, 4607};
//        int[] weight = new int[]{9203, 8288, 8812, 3535, 7013, 233, 447, 2854, 339, 5603};
//        System.out.println(new BestSeqAtIndex().bestSeqAtIndex2(height, weight));

        //case 4
//        int[] height = new int[]{4613, 5052, 7515, 6067, 7917, 4421, 5678, 736, 4473, 6820};
//        int[] weight = new int[]{5285, 3978, 8291, 7080, 95, 684, 8742, 4342, 5193, 2198};
//        System.out.println(new BestSeqAtIndex().bestSeqAtIndex2(height, weight));

        //case 5
//        int[] height = new int[]{6410, 4261, 939, 8469, 3780, 6773, 2255, 474, 6940, 5581};
//        int[] weight = new int[]{825, 9198, 4361, 8999, 8365, 7399, 6664, 218, 98, 5210};
//        System.out.println(new BestSeqAtIndex().bestSeqAtIndex2(height, weight));
        // case 5
        int[] height = new int[]{2868, 5485, 1356, 1306, 6017, 8941, 7535, 4941, 6331, 6181};
        int[] weight = new int[]{5042, 3995, 7985, 1651, 5991, 7036, 9391, 428, 7561, 8594};
        System.out.println(new BestSeqAtIndex().bestSeqAtIndex2(height, weight));
    }
}
