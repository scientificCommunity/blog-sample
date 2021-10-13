package org.baichuan.example.notion.dto

import org.baichuan.example.notion.bean.Holder

/**
 * @author: tk (rivers.boat.snow@gmail.com)
 * @date: 2021/10/11
 */
class UpdatePageDTO {
    lateinit var pageId: String
    lateinit var properties: Map<String, Holder>
}