package org.baichuan.sample.algorithms.leetcode.middle

class SwapPairs {
    fun swapPairs(head: ListNode?): ListNode? {
        if (head?.next == null) {
            return head
        }
        val newHead = head.next
        //交换后head变成后面的节点，所以head需要连接递归后的后续链表
        head.next = swapPairs(newHead?.next)
        //第二个节点变成头节点, 原头节点跟在第二个节点后
        newHead?.next = head
        return newHead
    }
}