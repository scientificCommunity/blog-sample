package com.baichuan.example.unit_test.usage.annotation

/**
 * @author: tk (rivers.boat.snow@gmail.com)
 * @date: 2021/9/13
 */
class Bar {
    lateinit var field: String
    fun helloWorld() {
        println("hello world from bar")
    }

    fun printField() {
        println("field in bar is $field")
    }
}