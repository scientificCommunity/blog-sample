package org.baichuan.sample.algorithms.leetcode.middle;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: kuntang (rivers.boat.snow@gmail.com)
 * @date: 2022/4/18
 * 面试题 17.13. 恢复空格
 * 解法：dp+字典树
 * https://leetcode-cn.com/problems/re-space-lcci/
 * 哦，不！你不小心把一个长篇文章中的空格、标点都删掉了，并且大写也弄成了小写。像句子"I reset the computer. It still didn’t boot!"已经变成了"iresetthecomputeritstilldidntboot"。在处理标点符号和大小写之前，你得先把它断成词语。当然了，你有一本厚厚的词典dictionary，不过，有些词没在词典里。假设文章用sentence表示，设计一个算法，把文章断开，要求未识别的字符最少，返回未识别的字符数。
 * <p>
 * 注意：本题相对原题稍作改动，只需返回未识别的字符数
 * <p>
 * <p>
 * <p>
 * 示例：
 * <p>
 * 输入：
 * dictionary = ["looked","just","like","her","brother"]
 * sentence = "jesslookedjustliketimherbrother"
 * 输出： 7
 * 解释： 断句后为"jess looked just like tim her brother"，共7个未识别字符。
 * 提示：
 * <p>
 * 0 <= len(sentence) <= 1000
 * dictionary中总字符数不超过 150000。
 * 你可以认为dictionary和sentence中只包含小写字母。
 * <p>
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/re-space-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Respace {
    class TrieNode {
        TrieNode[] next = new TrieNode[26];
        boolean end = false;

        //按字符顺序倒序构建字典树
        private void init(String[] dic) {
            for (String s : dic) {
                TrieNode node = this;
                for (int i = s.length() - 1; i >= 0; i--) {
                    int index = s.charAt(i) - 'a';
                    if (node.next[index] == null) {
                        node.next[index] = new TrieNode();
                    }
                    node = node.next[index];
                }
                node.end = true;
            }
        }
    }

    /**
     * 参考：https://leetcode-cn.com/problems/re-space-lcci/solution/jian-dan-dp-trieshu-bi-xu-miao-dong-by-sweetiee/
     * 状态定义：dp[i]表示从sentence第0个到第i个字符组成的句子里未识别的最少字符数，所以dp[len(sentence)]就是最终结果
     * 状态转移方程: dp[i]=min(dp[idx], dp[i - 1] + 1)
     * 解释: 当i向右移动一位，有两种情况：
     * 1. 不去匹配第i+1个字符（不管字典树里有没有这个字，都不去匹配。此时如果恰好存在一种情况：从n到i+1组成的字符被匹配到了(n为dp[i]里以i结尾的未匹配的连续字符串)，则这种情况可以由第二种转移方式处理。），则dp[i+1] = dp[i] + 1
     * 2. 尝试匹配第i+1个字符，即从后往前遍历前 i 个字符，若以其中某一个下标 idx 为开头（可能有多个）、以第 i + 1 个字符为结尾的字符串正好在词典里，则此时 dp[i + 1] = min(dp[idx]),然后再拿这个最小值跟第一种情况做对比并取较小值。
     * <p>
     * 另外：看起来第二种情况似乎得到的结果比第一种情况得到的值更小。但仔细一想，如果第i+1个字符真的匹配到了字典中以这个字符结尾的词，那么，消耗掉这个词在sentence中对应的字符之后，剩下的字符匹配完后所剩下的无法匹配的字符树不一定比dp[i]+1少。
     */
    public int respace(String[] dictionary, String sentence) {
        TrieNode root = new TrieNode();
        root.init(dictionary);
        int length = sentence.length();
        int[] dp = new int[length + 1];
        for (int i = 0; i < length; i++) {
            dp[i + 1] = dp[i] + 1;
            for (int j : doFind(root, sentence, i)) {
                dp[i + 1] = Math.min(dp[i + 1], dp[j]);
            }
        }
        return dp[length];
    }

    private List<Integer> doFind(TrieNode root, String sentence, int beginIndex) {
        TrieNode node = root;
        List<Integer> result = new ArrayList<>();
        for (int i = beginIndex; i >= 0; i--) {
            int index = sentence.charAt(i) - 'a';
            if (node.next[index] == null) {
                return result;
            } else {
                node = node.next[index];
            }
            if (node.end) {
                result.add(i);
            }
        }
        return result;
    }
}
