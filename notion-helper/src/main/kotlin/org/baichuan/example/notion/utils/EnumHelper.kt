package org.baichuan.example.notion.utils

import org.baichuan.example.notion.bean.Option
import org.baichuan.example.notion.enum.AccountEnum

/**
 * @author: tk (rivers.boat.snow@gmail.com)
 * @date: 2021/10/11
 */
object EnumHelper {
    fun <T : Enum<T>> constructEnumFromValueStr(input: String, clazz: Class<T>): T {
        val method = clazz.getMethod("values")
        val values = method.invoke(clazz) as Array<T>
        var result: T? = null
        values.forEach {
            val field = it.javaClass.getDeclaredField("value")
            field.isAccessible = true
            val value = field.get(it)
            if (value == input) {
                result = it
                return@forEach
            }
        }
        if (result == null) {
            println("++++++++++++++$input")
        }
        return result!!
    }
}