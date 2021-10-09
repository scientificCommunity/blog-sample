package org.baichuan.example.notion.utils

import org.apache.commons.io.FileUtils
import java.io.File
import java.nio.charset.Charset

/**
 * @author: tk (rivers.boat.snow@gmail.com)
 * @date: 2021/10/8
 */
object FileUtils {
    fun readFileToString(path: String): String {
        val file = File(path)
        return FileUtils.readFileToString(file, Charset.forName("GBK"))
    }
}

fun main() {
    val readFileToString =
        org.baichuan.example.notion.utils.FileUtils.readFileToString("/Users/tk/Downloads/alipay_record_20211001_154815.csv")
    val line = readFileToString.split("\n")
    val column = line[1].split(",")
    println(column[3])
    //println(readFileToString)
    println(line[1])
}