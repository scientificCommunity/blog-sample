package com.baichuan.example.unit_test.service

import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import java.util.stream.Stream

/**
 * @author: tk (rivers.boat.snow@gmail.com)
 * @date: 2021/9/17
 */
class ArgumentSourceValueFactory : ArgumentsProvider {
    override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> {
        return Stream.of(Arguments.of(3))
    }
}