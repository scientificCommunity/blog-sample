package org.baichuan.sample.algorithms.leetcode.middle

class NextPermutation {

    fun nextPermutation(nums: IntArray) {
        var i = nums.size - 1
        var flag = true
        while (i >= 0 && flag) {
            var j = nums.size - 1
            while (j > i) {
                if (nums[i] < nums[j]) {
                    var tmp = nums[i]
                    nums[i] = nums[j];
                    nums[j] = tmp
                    var l = i + 1
                    var r = nums.size - 1
                    while (l < r) {
                        tmp = nums[l]
                        nums[l] = nums[r];
                        nums[r] = tmp
                        l++
                        r--
                    }
                    flag = false
                    break
                }
                j--
            }
            i--
        }
        if (flag) {
            nums.reverse()
            println(nums.contentToString())
        } else {
            println(nums.contentToString())
        }
    }

    fun nextPermutation1(nums: IntArray) {
        if (nums.size == 1) print(nums.contentToString())
        var i = nums.size - 2
        var flag = true
        while (i >= 0 && flag) {
            var j = i + 1
            if (nums[i] < nums[j]) {
                var k = j
                while (j < nums.size - 1) {
                    j++
                    if (nums[i] < nums[j]) {
                        k = j
                    }
                }

                j = k
                var tmp = nums[i]
                nums[i] = nums[j];
                nums[j] = tmp
                var l = i + 1
                var r = nums.size - 1
                while (l < r) {
                    tmp = nums[l]
                    nums[l] = nums[r];
                    nums[r] = tmp
                    l++
                    r--
                }
                flag = false
                break
            }
            i--
        }
        if (flag) {
            nums.reverse()
            println(nums.contentToString())
        } else {
            println(nums.contentToString())
        }
    }
}

fun main() {
    NextPermutation().nextPermutation(arrayOf(3, 2, 1).toIntArray())
    val arrayOf = arrayOf(1, 2, 3)

    arrayOf.forEach(System.out::print)
    println(arrayOf.joinToString(","))
    println(arrayOf.contentToString())
}