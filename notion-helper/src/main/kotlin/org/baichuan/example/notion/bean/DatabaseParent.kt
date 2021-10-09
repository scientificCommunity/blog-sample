package org.baichuan.example.notion.bean

import com.alibaba.fastjson.annotation.JSONField

/**
 * @author: tk (rivers.boat.snow@gmail.com)
 * @date: 2021/10/8
 */
class DatabaseParent : Parent() {
    @JSONField(name = "database_id")
    lateinit var databaseId: String
}