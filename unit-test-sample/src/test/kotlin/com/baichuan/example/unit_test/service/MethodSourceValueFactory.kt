package com.baichuan.example.unit_test.service

import org.junit.jupiter.params.provider.Arguments
import java.util.stream.IntStream
import java.util.stream.Stream

/**
 * 用来为[org.junit.jupiter.params.provider.MethodSource]提供参数值
 * </p>
 *
 * 可以定义多个工厂方法来提供参数值，这点相对来讲比[org.junit.jupiter.params.provider.ArgumentsSource]
 * 因为[org.junit.jupiter.params.provider.ArgumentsSource]的工厂类被接口限制了，只能输出一个特定的Stream。
 *
 * 其实本质上来讲，MethodSource是ArgumentSource的一个扩展，参考:[org.junit.jupiter.params.provider.MethodArgumentsProvider]
 * @author: tk (rivers.boat.snow@gmail.com)
 * @date: 2021/9/17
 */
object MethodSourceValueFactory {
    @JvmStatic
    fun int(): IntStream {
        return IntStream.of(2)
    }

    /**
     * 返回自定义对象
     */
    @JvmStatic
    fun customObj(): Stream<out Arguments> {
        return Stream.of(Arguments.of(BarService()))
    }
}