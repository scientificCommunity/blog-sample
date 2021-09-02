package org.baichuan.example.spring.springfox

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan
import springfox.documentation.oas.annotations.EnableOpenApi

/**
 * @author: tk (rivers.boat.snow@gmail.com)
 * @date: 2021/9/2
 */
@SpringBootApplication
@EnableOpenApi
@ComponentScan(basePackages = ["org.baichuan.example.spring.springfox"])
open class Application {
}

fun main(args: Array<String>) {
    runApplication<Application>(*args)
}