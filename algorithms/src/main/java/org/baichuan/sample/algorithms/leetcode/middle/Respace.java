package org.baichuan.sample.algorithms.leetcode.middle;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: kuntang (rivers.boat.snow@gmail.com)
 * @date: 2022/4/18
 * 面试题 17.13. 恢复空格
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
