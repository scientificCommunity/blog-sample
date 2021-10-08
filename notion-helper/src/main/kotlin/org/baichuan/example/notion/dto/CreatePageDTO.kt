package org.baichuan.example.notion.dto

import org.baichuan.example.notion.bean.Parent

/**
 * @author: tk (rivers.boat.snow@gmail.com)
 * @date: 2021/10/8
 */
class CreatePageDTO {
    lateinit var parent: Parent
    lateinit var properties: Map<String, Map<String, Any>>
}