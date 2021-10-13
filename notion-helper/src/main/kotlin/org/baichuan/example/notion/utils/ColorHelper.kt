package org.baichuan.example.notion.utils

import org.apache.commons.lang3.RandomUtils
import org.baichuan.example.notion.enum.ColorEnum

object ColorHelper {
    @JvmStatic
    fun getRandomColor(): String {
        val values = ColorEnum.values()
        val i = RandomUtils.nextInt() % values.size
        return values[i].value
    }

    fun setColor(key: String): String {
        if (colorCache.containsKey(key)) {
            return colorCache[key]!!
        }
        val randomColor = getRandomColor()
        colorCache[key] = randomColor
        return randomColor
    }

    private val colorCache: MutableMap<String, String> = HashMap()
}