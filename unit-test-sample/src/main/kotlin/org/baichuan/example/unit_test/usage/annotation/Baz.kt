package org.baichuan.example.unit_test.usage.annotation

/**
 * @author: tk (rivers.boat.snow@gmail.com)
 * @date: 2021/9/13
 */
class Baz {
    lateinit var field: String
    fun helloWorld() {
        println("hello world from Baz")
    }

    fun printField() {
        println("field in Baz is $field")
    }
}