package org.baichuan.sample.algorithms.leetcode.simple


/**
 * 21. 合并两个有序链表
 */
class MergeTwoLists {
    fun mergeTwoLists(list1: ListNode?, list2: ListNode?): ListNode? {
        if (list1 == null) return list2
        if (list2 == null) return list1

        return if (list1.`val` > list2.`val`) {
            //下一步是list2的next跟list1做大小比对
            list2.next = mergeTwoLists(list1, list2.next)
            list2
        } else {
            list1.next = mergeTwoLists(list2, list1.next)
            list1
        }
    }
}

class ListNode(var `val`: Int) {
    var next: ListNode? = null
}