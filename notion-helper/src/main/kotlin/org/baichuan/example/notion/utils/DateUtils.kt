package org.baichuan.example.notion.utils

import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

object DateUtils {
    fun toZonedDateStr(dateStr: String): String {
        val parse = LocalDateTime.parse(dateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss "))
        return parse.atZone(ZoneId.of("+8")).toString()
    }
}