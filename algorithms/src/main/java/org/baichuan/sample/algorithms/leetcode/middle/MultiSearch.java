package org.baichuan.sample.algorithms.leetcode.middle;

import java.util.*;

/**
 * @author: kuntang (rivers.boat.snow@gmail.com)
 * @date: 2022/4/17
 * 面试题 17.17. 多次搜索
 * https://leetcode-cn.com/problems/multi-search-lcci/
 */
public class MultiSearch {
    class TrieNode {
        TrieNode[] next = new TrieNode[26];
        String word = null;
    }

    TrieNode root = new TrieNode();

    public int[][] multiSearch(String big, String[] smalls) {
        constructTrieNode(smalls);
        Map<String, List<Integer>> hit = new HashMap<>();
        for (int i = 0; i < big.length(); i++) {
            List<String> matches = search(big, i);
            for (String match : matches) {
                if (!hit.containsKey(match)) {
                    ArrayList<Integer> positions = new ArrayList<>();
                    positions.add(i);
                    hit.put(match, positions);
                    continue;
                }
                hit.get(match).add(i);
            }
        }
        int[][] result = new int[smalls.length][];
        for (int i = 0; i < smalls.length; i++) {
            if (hit.containsKey(smalls[i])) {
                result[i] = hit.get(smalls[i]).stream().mapToInt(Integer::intValue).toArray();
            } else {
                result[i] = new int[0];
            }
        }
        return result;
    }

    private void constructTrieNode(String[] smalls) {
        for (String s : smalls) {
            TrieNode node = root;
            for (char c : s.toCharArray()) {
                if (node.next[c - 'a'] == null) {
                    node.next[c - 'a'] = new TrieNode();
                }
                node = node.next[c - 'a'];
            }
            node.word = s;
        }
    }

    private List<String> search(String big, int beginIndex) {
        List<String> result = new ArrayList<>();

        char[] chars = big.toCharArray();
        TrieNode node = root;
        for (int i = beginIndex; i < chars.length; i++) {
            if (node.next[chars[i] - 'a'] == null) {
                return result;
            }
            node = node.next[chars[i] - 'a'];
            if (node.word != null) {
                result.add(node.word);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        String big = "mississippi";
        String[] smalls = {"is", "ppi", "hi", "sis", "i", "ssippi"};
        int[][] ints = new MultiSearch().multiSearch(big, smalls);
        System.out.println(ints);
    }
}
