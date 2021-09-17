# unit-test-sample
junit系列

### 踩坑日记之浅析Mockito mock Kotlin Object类方法报错
比如我创建一个Kotlin Object类：[ObjectMethod](src/main/kotlin/com/baichuan/example/unit_test/ObjectMethod.kt)
```kotlin
package com.baichuan.example.unit_test

object ObjectMethod {

    fun doSomething() {
        println("this is ObjectMethod#doSomething")
    }

    @JvmStatic
    fun doSomethingWithJvmStatic() {
        println("this is ObjectMethod#doSomethingWithJvmStatic")
    }
}
```

如果我直接去mock该类的doSomething方法，会报错。

```kotlin
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
```

这是因为`kotlin`里的`object`类里的方法虽然在`kotlin`里从形态跟使用上来看与静态方法无二。但是编译成java代码后，其本质其实是内部初始化了一个当前类的静态常量实例`INSTANCE`。这个INSTANCE在kotlin语法里被隐藏了，但在java里依然可以显示访问。ObjectMethod编译成java后的代码如下：

```java
public final class ObjectMethod {
   @NotNull
   public static final ObjectMethod INSTANCE = new ObjectMethod();

   private ObjectMethod() {
   }

   public final void doSomething() {
      String var1 = "this is ObjectMethod#doSomething";
      boolean var2 = false;
      System.out.println(var1);
   }

   public static void doSomethingWithJvmStatic() {
      String var0 = "this is ObjectMethod#doSomethingWithJvmStatic";
      boolean var1 = false;
      System.out.println(var0);
   }
}
```

所以，不能`mock` `ObjectMethod#doSomething`本质上的原因是正常手段无法mock静态常量。如果想要使kotlin的object类中的方法能够被mock，只需在方法上加上@JvmStatic注解即可。被其标注的方法会被编译成普通的java静态方法。

上面说正常手段无法mock静态常量，那么非正常手段呢？其实这个非正常手段就是通过反射将被mock过的实例注入到ObjectMethod中即可。

```kotlin
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
```

[ReflectionUtils](src/main/kotlin/com/baichuan/example/unit_test/ReflectionUtils.kt)

```kotlin
package com.baichuan.example.unit_test

import java.lang.reflect.Field
import java.lang.reflect.Modifier

object ReflectionUtils {
    @Throws(Exception::class)
    fun setFinalStatic(field: Field, newValue: Any) {
        field.isAccessible = true
        val modifiersField: Field = Field::class.java.getDeclaredField("modifiers")
        modifiersField.isAccessible = true
        modifiersField.setInt(field, field.modifiers and Modifier.FINAL.inv())
        field.set(null, newValue)
    }
}
```
