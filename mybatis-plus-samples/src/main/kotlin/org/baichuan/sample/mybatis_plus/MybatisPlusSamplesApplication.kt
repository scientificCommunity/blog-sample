package org.baichuan.sample.mybatis_plus

import org.mybatis.spring.annotation.MapperScan
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

/**
 * @author: tk (rivers.boat.snow@gmail.com)
 * @date: 2021/12/6
 */
@MapperScan("org.baichuan.sample.mybatis_plus.mapper")
@SpringBootApplication
open class MybatisPlusSamplesApplication {
}

fun main(args: Array<String>) {
    runApplication<MybatisPlusSamplesApplication>(*args)
}