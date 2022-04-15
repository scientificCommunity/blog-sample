package org.baichuan.sample.algorithms.leetcode.middle

/**
 * @author: kuntang (rivers.boat.snow@gmail.com)
 * @date: 2022/4/12
 */
class ReverseBetween {
    fun reverseBetween(head: ListNode?, left: Int, right: Int): ListNode? {
        if (head == null) {
            return null
        }
        if (left == right) {
            return head
        }
        var currIndex = 0
        var currNode = head
        var preNode: ListNode? = null
        var leftNode: ListNode? = head
        var rightNode: ListNode?

        var reverseRightNode: ListNode? = head
        var reverseLeftNode: ListNode? = head
        while (currNode != null) {
            val nextNode = currNode.next
            if (currIndex > left - 2 && currIndex < right) {
                if (currIndex == left - 1) {
                    leftNode = preNode
                    reverseRightNode = currNode
                }
                if (currIndex == right - 1) {
                    rightNode = nextNode
                    reverseRightNode?.next = rightNode
                    reverseLeftNode = currNode
                    leftNode?.next = reverseLeftNode
                }
                currNode.next = preNode
            }
            preNode = currNode
            currNode = nextNode
            currIndex++
        }
        return if (left == 1) {
            reverseLeftNode
        } else {
            head
        }
    }

    private fun doReverse(head: ListNode?) {
        if (head == null) {
            return
        }
        var currNode = head
        var preNode: ListNode? = null
        while (currNode != null) {
            val nextNode = currNode.next
            currNode.next = preNode
            preNode = currNode
            currNode = nextNode
        }
    }
}

fun main() {
    //case 1
    val a = ListNode(1)
    val b = ListNode(2)
    val c = ListNode(3)
    val d = ListNode(4)
    val e = ListNode(5)
    a.next = b
    b.next = c
    c.next = d
    d.next = e
    val reverseBetween = ReverseBetween()
    val reverseBetween1 = reverseBetween.reverseBetween(a, 2, 4)

    //case 2
//    val a = ListNode(3)
//    val b = ListNode(5)
//    a.next = b
//    val reverseBetween = ReverseBetween()
//    val reverseBetween1 = reverseBetween.reverseBetween(a, 1, 2)


    println(reverseBetween1)
}

class ListNode(var `val`: Int) {
    var next: ListNode? = null
}