package org.baichuan.example.notion.utils

import com.alibaba.fastjson.JSON
import com.alibaba.fastjson.JSONObject
import org.apache.http.client.methods.HttpPatch
import org.apache.http.client.methods.HttpPost
import org.apache.http.entity.ByteArrayEntity
import org.apache.http.entity.ContentType
import org.apache.http.impl.client.HttpClients
import org.apache.http.util.EntityUtils
import org.baichuan.example.notion.Main
import org.baichuan.example.notion.bean.*
import org.baichuan.example.notion.dto.CreateDatabaseDTO
import org.baichuan.example.notion.dto.CreatePageDTO
import org.baichuan.example.notion.dto.UpdatePageDTO

/**
 * @author: tk (rivers.boat.snow@gmail.com)
 * @date: 2021/10/11
 */
object OperationHelper {
    fun updatePage(updatePageDTO: UpdatePageDTO): String {
        val httpClient = HttpClients.createDefault()
        val httpPost = HttpPatch("$OPERATE_PAGE_PATH/${updatePageDTO.pageId}")
        httpPost.setHeaders(Main.initHeader())

        httpPost.entity = ByteArrayEntity(JSON.toJSONString(updatePageDTO).toByteArray(), ContentType.APPLICATION_JSON)

        val response = httpClient.execute(httpPost)

        val json = JSONObject.parseObject(EntityUtils.toString(response.entity))

        return json["id"].toString()
    }

    fun updatePage(pageId: String, properties: Map<String, Holder>): String {
        val updatePageDTO = UpdatePageDTO()
        updatePageDTO.pageId = pageId
        updatePageDTO.properties = properties

        return updatePage(updatePageDTO)
    }

    fun createPage(parentId: String, properties: Map<String, Holder>): String {
        val parent = DatabaseParent()
        parent.databaseId = parentId

        val createPageDTO = CreatePageDTO()
        createPageDTO.properties = properties
        createPageDTO.parent = parent

        val httpClient = HttpClients.createDefault()
        val httpPost = HttpPost(OPERATE_PAGE_PATH)
        httpPost.setHeaders(Main.initHeader())

        httpPost.entity = ByteArrayEntity(JSON.toJSONString(createPageDTO).toByteArray(), ContentType.APPLICATION_JSON)

        val response = httpClient.execute(httpPost)

        val json = JSONObject.parseObject(EntityUtils.toString(response.entity))

        return json["id"].toString()
    }

    fun createDatabase(parentId: String, properties: Map<String, Holder>, databaseTitle: String): String {
        val createDatabaseDTO = CreateDatabaseDTO()
        createDatabaseDTO.properties = properties

        val parent = PageParent()
        parent.pageId = parentId
        createDatabaseDTO.parent = parent

        val title = Title()
        val text = Text()
        text.content = databaseTitle
        title.text = text
        createDatabaseDTO.title = arrayOf(title)

        val httpClient = HttpClients.createDefault()
        val httpPost = HttpPost("https://api.notion.com/v1/databases/")
        httpPost.setHeaders(Main.initHeader())

        httpPost.entity =
            ByteArrayEntity(JSON.toJSONString(createDatabaseDTO).toByteArray(), ContentType.APPLICATION_JSON)

        val response = httpClient.execute(httpPost)

        val json = JSONObject.parseObject(EntityUtils.toString(response.entity))

        return json["id"].toString()
    }

    const val NOTION_HOST = "https://api.notion.com/v1"
    const val OPERATE_PAGE_PATH = "$NOTION_HOST/pages"
}