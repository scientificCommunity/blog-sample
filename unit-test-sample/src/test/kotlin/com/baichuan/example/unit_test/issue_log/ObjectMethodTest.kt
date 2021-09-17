package com.baichuan.example.unit_test.issue_log

import com.baichuan.example.unit_test.ReflectionUtils
import com.baichuan.example.unit_test.StaticMethod
import org.junit.jupiter.api.*
import org.mockito.MockedStatic
import org.mockito.Mockito
import org.mockito.exceptions.misusing.MissingMethodInvocationException

/**
 * 使用Mockito去mock kotlin object fun会引发的问题以及怎么解决
 *
 * @author: tk (rivers.boat.snow@gmail.com)
 * @date: 2021/9/12
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ObjectMethodTest {
    @BeforeAll
    fun init() {
        ObjectMethod.doSomethingWithJvmStatic()
        mockStaticObjectMethod = Mockito.mockStatic(ObjectMethod::class.java)
    }

    @AfterAll
    fun destroy() {
        mockStaticObjectMethod!!.close()
    }

    var mockStaticObjectMethod: MockedStatic<ObjectMethod>? = null

    @Test
    @DisplayName("mock普通的kotlin静态方法")
    fun testMockKotlinObject() {
        //mockStaticObjectMethod = Mockito.mockStatic(ObjectMethod::class.java)
        Assertions.assertThrows(MissingMethodInvocationException::class.java) {
            mockStaticObjectMethod!!.`when`<Unit>(
                ObjectMethod::doSomething
            ).thenAnswer { println("this is mocked Object#doSomething") }
        }

        ObjectMethod.doSomething()

        //mockStaticObjectMethod!!.close()
    }

    @Test
    @DisplayName("通过反射修改静态常量来mock普通的kotlin静态方法")
    fun testMockKotlinObjectMethodByReflection() {
        val mock = Mockito.mock(ObjectMethod::class.java)
        Mockito.`when`(ObjectMethod.doSomething()).then {
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

        //mockStaticObjectMethod = Mockito.mockStatic(ObjectMethod::class.java)
        mockStaticObjectMethod!!.`when`<Unit>(
            ObjectMethod::doSomethingWithJvmStatic
        ).thenAnswer { println("this is mocked Object#doSomethingWithJvmStatic") }

        ObjectMethod.doSomethingWithJvmStatic()

        //mockStaticObjectMethod!!.close()
    }

    @Test
    fun testMockJavaStatic() {
        val mockStatic = Mockito.mockStatic(StaticMethod::class.java)
        Mockito.`when`(StaticMethod.doSomething())
            .thenAnswer { println("this is mocked StaticMethod#doSomething") }

        StaticMethod.doSomething()

        mockStatic.close()
    }
}