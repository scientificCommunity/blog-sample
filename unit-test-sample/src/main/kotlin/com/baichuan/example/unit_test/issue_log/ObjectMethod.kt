package com.baichuan.example.unit_test.issue_log

object ObjectMethod {

    fun doSomething() {
        println("this is ObjectMethod#doSomething")
    }

    @JvmStatic
    fun doSomethingWithJvmStatic() {
        println("this is ObjectMethod#doSomethingWithJvmStatic")
    }
}