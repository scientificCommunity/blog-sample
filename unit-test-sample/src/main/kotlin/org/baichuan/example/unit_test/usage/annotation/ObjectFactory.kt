package org.baichuan.example.unit_test.usage.annotation

/**
 * @author: tk (rivers.boat.snow@gmail.com)
 * @date: 2021/9/13
 */
object ObjectFactory {
    fun foo(): Foo {
        val foo = Foo()
        foo.field = "【field by ObjectFactory】"
        return foo
    }

    fun baz(): Baz {
        val baz = Baz()
        baz.field = "【field by ObjectFactory】"
        return baz
    }
}