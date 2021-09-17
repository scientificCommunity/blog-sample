package com.baichuan.example.unit_test.service

import org.junit.jupiter.api.*
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ArgumentsSource
import org.junit.jupiter.params.provider.MethodSource
import org.junit.jupiter.params.provider.ValueSource
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import java.lang.IllegalArgumentException

/**
 * @author: tk (rivers.boat.snow@gmail.com)
 * @date: 2021/9/17
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class FooTest {

    @InjectMocks
    lateinit var fooService: FooService

    @Mock
    lateinit var barService: BarService

    @BeforeAll
    fun init() {
        MockitoAnnotations.openMocks(this)
    }

    @DisplayName("返回true")
    @ParameterizedTest(name = ParameterizedTest.ARGUMENTS_PLACEHOLDER)
    @ValueSource(ints = [1])
    fun shouldReturnTrueFooTest(arg: Int) {
        Assertions.assertTrue(fooService.foo(arg))
    }

    @DisplayName("返回false")
    @ParameterizedTest(name = ParameterizedTest.ARGUMENTS_PLACEHOLDER)
    @MethodSource(value = ["com.baichuan.example.unit_test.service.MethodSourceValueFactory#int"])
    fun shouldReturnFalseFooTest(arg: Int) {
        Assertions.assertFalse(fooService.foo(arg))
    }

    @DisplayName("执行异常")
    @ParameterizedTest(name = ParameterizedTest.ARGUMENTS_PLACEHOLDER)
    @ArgumentsSource(value = ArgumentSourceValueFactory::class)
    fun shouldFailFooTest(arg: Int) {
        assertThrows<IllegalArgumentException> { fooService.foo(arg) }
        //Assertions.assertThrows(IllegalArgumentException::class.java) { fooService.foo(arg) }
    }
}