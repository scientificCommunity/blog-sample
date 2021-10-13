package org.baichuan.example.notion.bean

import com.alibaba.fastjson.annotation.JSONField

/**
 * @author: tk (rivers.boat.snow@gmail.com)
 * @date: 2021/10/10
 */
class CreateRichTextHolder : Holder {
    @JSONField(name = "rich_text")
    var richText: Array<RichText>? = null
}