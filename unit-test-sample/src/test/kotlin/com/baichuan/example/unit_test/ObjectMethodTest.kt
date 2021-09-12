package com.baichuan.example.unit_test

import org.junit.jupiter.api.*
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.exceptions.misusing.MissingMethodInvocationException

/**
 * @author: tk (rivers.boat.snow@gmail.com)
 * @date: 2021/9/12
 */
class ObjectMethodTest {
    @BeforeEach
    fun init() {

    }

    @Test
    @DisplayName("mock普通的kotlin静态方法")
    fun testMockKotlinObject() {
        Assertions.assertThrows(MissingMethodInvocationException::class.java) {
            Mockito.mockStatic(ObjectMethod::class.java).`when`<Unit>(
                ObjectMethod::doSomething
            ).thenAnswer { println("this is mocked Object#doSomething") }
        }

        ObjectMethod.doSomething()
    }

    @Test
    @DisplayName("通过反射修改静态常量来mock普通的kotlin静态方法")
    fun testMockKotlinObjectMethodByReflection() {
        val mock = Mockito.mock(ObjectMethod::class.java)
        Mockito.`when`(mock.doSomething()).then {
            print("this is mocked ObjectMethod by reflection")
        }
        val declaredMethod = ObjectMethod::class.java.getDeclaredField("INSTANCE")
        ReflectionUtils.setFinalStatic(declaredMethod, mock)

        ObjectMethod.doSomething()
    }

    @Test
    @DisplayName("mock带JvmStatic注解的kotlin方法")
    fun testMockKotlinObjectWithJvmStatic() {
        ObjectMethod.doSomethingWithJvmStatic()

        Mockito.mockStatic(ObjectMethod::class.java).`when`<Unit>(
            ObjectMethod::doSomethingWithJvmStatic
        ).thenAnswer { println("this is mocked Object#doSomethingWithJvmStatic") }

        ObjectMethod.doSomethingWithJvmStatic()
    }

    @Test
    fun testMockJavaStatic() {
        Mockito.mockStatic(StaticMethod::class.java)
        Mockito.`when`(StaticMethod.doSomething())
            .thenAnswer { println("this is mocked StaticMethod#doSomething") }

        StaticMethod.doSomething()
    }
}