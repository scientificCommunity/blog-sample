package org.baichuan.sample.algorithms.leetcode.simple.interview

/**
 * https://leetcode.cn/problems/binode-lcci/
 * 面试题 17.12. BiNode
 */
class ConvertBiNode {
    var head: TreeNode? = null
    var pre: TreeNode? = null
    fun convertBiNode(root: TreeNode?): TreeNode? {
        recursion(root)
        return head
    }

    fun recursion(root: TreeNode?) {
        if (root == null) {
            return
        }
        //左边先走到底
        recursion(root.left)
        if (head == null) {
            head = root
            pre = head
        } else {
            pre?.right = root
            root.left = null
            pre = root
        }
        //根节点与pre的连接关系处理完后，将pre跟新为根节点。然后处理根节点根右子树的关系（必然先递归到右子树的最小节点才会出来）
        recursion(root.right)
    }
}

fun main() {
    val t1 = TreeNode(4)
    val t2 = TreeNode(2)
    val t3 = TreeNode(1)
    val t4 = TreeNode(3)
    val t5 = TreeNode(6)
    val t6 = TreeNode(0)
    val t7 = TreeNode(5)
    t1.left = t2
    t1.right = t7
    t2.left = t3
    t2.right = t4
    t7.right = t5
    t3.left = t6
    ConvertBiNode().recursion(t1)
}

class TreeNode(var `val`: Int) {
    var left: TreeNode? = null
    var right: TreeNode? = null
}