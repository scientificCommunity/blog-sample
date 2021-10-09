package org.baichuan.example.notion.bean

import com.alibaba.fastjson.annotation.JSONField

/**
 * @author: tk (rivers.boat.snow@gmail.com)
 * @date: 2021/10/9
 */
class CreateMultiSelectHolder : Holder {
    @JSONField(name = "multi_select")
    lateinit var multiSelect: Array<Select>
}