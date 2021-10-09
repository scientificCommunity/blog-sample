package org.baichuan.example.notion.dto

import org.baichuan.example.notion.bean.Holder
import org.baichuan.example.notion.bean.Parent

/**
 * @author: tk (rivers.boat.snow@gmail.com)
 * @date: 2021/10/9
 */
class NewCreatePageDTO {
    lateinit var parent: Parent
    lateinit var properties: Map<String, Holder>
}