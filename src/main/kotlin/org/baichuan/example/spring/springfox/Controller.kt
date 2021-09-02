package org.baichuan.example.spring.springfox

import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiResponse
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * @author: tk (rivers.boat.snow@gmail.com)
 * @date: 2021/9/2
 */
@Api("api")
@RestController
@RequestMapping("/payment")
class Controller {

    @ApiOperation("ali pay callback")
    @ApiResponse(
        code = 0,
        message = "ok",
    )
    @PostMapping("test", consumes = [MediaType.APPLICATION_FORM_URLENCODED_VALUE])
    fun test(model: CompiledKotlinModel) {

    }
}