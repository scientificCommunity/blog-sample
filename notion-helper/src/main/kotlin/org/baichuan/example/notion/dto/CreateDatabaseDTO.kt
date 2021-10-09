package org.baichuan.example.notion.dto

import org.baichuan.example.notion.bean.Holder
import org.baichuan.example.notion.bean.Parent
import org.baichuan.example.notion.bean.Title

/**
 * @author: tk (rivers.boat.snow@gmail.com)
 * @date: 2021/10/9
 */
class CreateDatabaseDTO {
    lateinit var parent: Parent
    lateinit var title: Array<Title>
    lateinit var properties: Map<String, Holder>
}