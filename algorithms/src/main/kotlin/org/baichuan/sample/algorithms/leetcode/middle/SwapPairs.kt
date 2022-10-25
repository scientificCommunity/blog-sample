package org.baichuan.sample.algorithms.leetcode.middle

/**
 * https://leetcode.cn/problems/swap-nodes-in-pairs/
 */
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

    /**
     * 非递归解法
     */
    fun swapPairs1(head: ListNode?): ListNode? {
        if (head?.next == null) {
            return head
        }
        var curr = head
        val result = head.next
        var pre: ListNode? = null
        while (curr?.next != null) {
            val newHead = curr.next
            val newCurr = newHead?.next
            newHead?.next = curr
            curr.next = newCurr
            pre?.next = newHead
            pre = curr
            if (newCurr == null) {
                break
            }

            curr = newCurr
        }
        return result
    }
}

fun main() {
    val head1 = ListNode(1)
    val head2 = ListNode(2)
    val head3 = ListNode(3)
//    val head4 = ListNode(4)
    head1.next = head2
    head2.next = head3
//    head3.next = head4
    SwapPairs().swapPairs1(head1)
}