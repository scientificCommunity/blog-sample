package org.baichuan.sample.algorithms.leetcode.middle;

/**
 * @author tk
 * @date 2022/9/16 12:48
 * https://leetcode.cn/problems/remove-nth-node-from-end-of-list/
 */
public class RemoveNthFromEnd {
    public static void main(String[] args) {
        ListNode listNode1 = new ListNode(1);
        ListNode listNode2 = new ListNode(2);
        ListNode listNode3 = new ListNode(3);
        ListNode listNode4 = new ListNode(4);
        ListNode listNode5 = new ListNode(5);
        listNode1.next = listNode2;
        listNode2.next = listNode3;
        listNode3.next = listNode4;
        listNode4.next = listNode5;

        ListNode listNode = new RemoveNthFromEnd().removeNthFromEnd(listNode1, 2);
        System.out.println(123);
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode[] nodes = new ListNode[32];

        ListNode curr = head;
        int k = 0;
        while (curr != null) {
            nodes[k] = curr;
            curr = curr.next;
            k++;
        }
        int len = k;

        if (n == len) {
            return head.next;
        }

        nodes[len - n - 1].next = nodes[len - n + 1];

        return head;
    }

    /**
     * Definition for singly-linked list.
     */
    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}
