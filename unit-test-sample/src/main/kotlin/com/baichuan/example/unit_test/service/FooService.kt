package com.baichuan.example.unit_test.service

import java.lang.IllegalArgumentException

/**
 * @author: tk (rivers.boat.snow@gmail.com)
 * @date: 2021/9/17
 */
class FooService {

    lateinit var barService: BarService

    fun foo(arg: Int): Boolean {
        return when (arg) {
            1 -> {
                println("The input arg is 1")
                true
            }
            2 -> {
                println("The input arg is $arg and to invoke BarService")
                barService.bar()
                false
            }
            else -> throw IllegalArgumentException()
        }
    }
}