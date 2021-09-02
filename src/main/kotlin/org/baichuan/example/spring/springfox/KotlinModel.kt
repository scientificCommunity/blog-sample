package org.baichuan.example.spring.springfox

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty

/**
 * @author: tk (rivers.boat.snow@gmail.com)
 * @date: 2021/9/2
 */
@ApiModel
class KotlinModel {
    @ApiModelProperty
    var field: String? = null
    @ApiModelProperty
    lateinit var field1: String

    /*fun setField(field: String?) {
        this.field = field
    }

    fun getField(): String? {
        return field
    }*/
}