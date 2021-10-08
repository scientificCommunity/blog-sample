package org.baichuan.example.notion

import com.alibaba.fastjson.JSON
import org.apache.http.client.methods.HttpPost
import org.apache.http.entity.ByteArrayEntity
import org.apache.http.entity.ContentType
import org.apache.http.impl.client.HttpClients
import org.apache.http.message.BasicHeader
import org.apache.http.util.EntityUtils
import org.baichuan.example.notion.bean.*
import org.baichuan.example.notion.dto.CreatePageDTO

/**
 * @author: tk (rivers.boat.snow@gmail.com)
 * @date: 2021/10/8
 */
class Main {

    companion object {
        @JvmStatic
        fun initHeader(): Array<BasicHeader> {
            return arrayOf(
                BasicHeader("Authorization", ""),
                BasicHeader("Content-Type", "application/json"),
                BasicHeader("Connection", "keep-alive"),
                BasicHeader("Notion-Version", "2021-05-13")
            )

        }
    }
}

fun main() {
    val parent = Parent()
    parent.databaseId = "89b5f36cff39413c97928c6b9c60ce15"

    val createPageDTO = CreatePageDTO()
    val map: MutableMap<String, Map<String, Any>> = HashMap()

    val title = Title()
    val text = Text()
    text.content = "test1"
    title.text = text
    map["Name"] = title.mapArray()

    val richText = RichText()
    val text1 = Text()
    text1.content = "test1 in richText"
    richText.text = text1
    map["Description"] = richText.mapArray()

    val select = Select()
    select.name = "Vegetable"
    map["Food group"] = select.map()

    val multiSelect = MultiSelect()
    multiSelect.name = "java test1"
    multiSelect.color = "yellow"
    map["Store availability"] = multiSelect.mapArray()

    createPageDTO.properties = map
    createPageDTO.parent = parent


    val httpClient = HttpClients.createDefault()

    val httpPost = HttpPost("https://api.notion.com/v1/pages/")
    httpPost.setHeaders(Main.initHeader())

    httpPost.entity = ByteArrayEntity(JSON.toJSONString(createPageDTO).toByteArray(), ContentType.APPLICATION_JSON)

    val response = httpClient.execute(httpPost)
    println(response.statusLine)
    println(EntityUtils.toString(response.entity))
}