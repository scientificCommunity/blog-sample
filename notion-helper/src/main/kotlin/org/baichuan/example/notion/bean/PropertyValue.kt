package org.baichuan.example.notion.bean

import org.baichuan.example.notion.utils.TeeUtils

/**
 * @author: tk (rivers.boat.snow@gmail.com)
 * @date: 2021/10/8
 */
abstract class PropertyValue {
    fun map(): Map<String, PropertyValue> {
        val map: MutableMap<String, PropertyValue> = HashMap()
        var key = TeeUtils.camelToUnderline(this.javaClass.simpleName)
        key = key!!.substring(1, key.length)
        map[key] = this
        return map
    }

    fun mapArray(): Map<String, Array<PropertyValue>> {
        val map: MutableMap<String, Array<PropertyValue>> = HashMap()
        var key = TeeUtils.camelToUnderline(this.javaClass.simpleName)
        key = key!!.substring(1, key.length)
        map[key] = arrayOf(this)
        return map
    }
}