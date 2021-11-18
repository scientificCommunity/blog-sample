package org.baichuan.example.unit_test.usage.annotation

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.mockito.*

/**
 * @author: tk (rivers.boat.snow@gmail.com)
 * @date: 2021/9/13
 */
class MockUsageTest {
    @InjectMocks
    lateinit var mockUsage: MockUsage

    /**
     * 生成一个Foo对象的mock对象，方法调用默认不执行任何行为
     */
    @Mock
    lateinit var foo: Foo

    /**
     * 生成一个Foo对象的mock对象，方法调用默认指向真实方法的执行
     */
    @Mock(answer = Answers.CALLS_REAL_METHODS)
    lateinit var bar: Bar

    /**
     * 生成一个Foo对象的spy实例，默认执行真实对象的行为
     * 与mock都可用于注入到被[InjectMocks]标注的对象中。类型相同的情况下name一致的优先注入。
     * 这里由于MockUsage里Foo属性的name是foo，所以优先注入上面被[Mock]标注的foo
     */
    @Spy
    val fooSpy: Foo = ObjectFactory.foo()

    @Spy
    val bazSpy: Baz = ObjectFactory.baz()

    @BeforeEach
    fun initBeforeEach() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    @DisplayName("注解基本用法")
    fun testBasicBehaviour() {
        //不会打印任何信息
        mockUsage.foo.helloWorld()
        //执行真实方法helloWorld，并打印
        mockUsage.bar.helloWorld()
        //打印出FooFactory设置的属性值
        fooSpy.printField()
        mockUsage.baz.printField()
    }

    @Test
    @DisplayName("Mockito创建mock对象")
    fun testByMockito() {
        //等同于@Mock
        val defaultFooMock = Mockito.mock(Foo::class.java)
        //等同于@Mock(answer = Answers.CALLS_REAL_METHODS)
        val callRealFooMock = Mockito.mock(Foo::class.java, Mockito.CALLS_REAL_METHODS)
        //自定义被mock对象方法调用的默认行为
        val customDefinedFooMock = Mockito.mock(Foo::class.java) {
            println("hello world by customDefinedMock")
            null
        }
        defaultFooMock.helloWorld()
        callRealFooMock.helloWorld()

        //下面两行都会执行println("hello world by customDefinedMock")
        customDefinedFooMock.helloWorld()
        customDefinedFooMock.printField()
    }

}