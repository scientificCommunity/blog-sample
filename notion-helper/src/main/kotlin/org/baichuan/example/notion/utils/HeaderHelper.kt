package org.baichuan.example.notion.utils

import org.apache.http.message.BasicHeader

/**
 * @author: tk (rivers.boat.snow@gmail.com)
 * @date: 2021/10/13
 */
object HeaderHelper {
    @JvmStatic
    fun genHeader(token: String): Array<BasicHeader> {
        return arrayOf(
            BasicHeader("Authorization", token),
            BasicHeader("Content-Type", "application/json"),
            BasicHeader("Connection", "keep-alive"),
            BasicHeader("Notion-Version", "2021-05-13")
        )

    }
}