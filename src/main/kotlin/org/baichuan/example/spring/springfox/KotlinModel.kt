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

    /**
     * lateinit编译后会会生成public修饰该字段，且会生成该字段的public get方法
     * 如果此时加上@ApiModelProperty会导致重复加载该字段的swagger属性
     * @see springfox.documentation.spring.web.readers.operation.OperationParameterReader.readParameters
     * @see springfox.documentation.spring.web.readers.parameter.ModelAttributeParameterExpander.expand
     * @see springfox.documentation.spring.web.readers.parameter.ModelAttributeParameterExpander.simpleFields
     * @see springfox.documentation.spring.web.plugins.DocumentationPluginsManager.expandParameter
     *
     *
     * 这里会判断字段上是否有加@ApiModelProperty，如果加了，则会根据该注解生成一个{@see RequestParameterBuilder}.
     * 且其scalarExample除了value以外其他的字段都是null。
     * 又因为{@link OperationParameterReader.apply line89}这里会将RequestParameter丢到一个HashSet中,
     * 所以首先会根据RequestParameter的hashCode判断是否发生hash碰撞，
     * 而public属性的字段也会被识别成RequestParameter（参考：{@link ModelAttributeParameterExpander.expand line 110}跟{@link ModelAttributeParameterExpander#allModelAttributes line 100这里会过滤掉所有修饰符不是public的属性}）。
     * 观看{@link RequestParameter#hashcode}可以知道
     * 字段对应的get方法的RequestParameter生成的hashcode肯定与字段本身的RequestParameter生成的hashcode是相同的
     * 所以这里就发生了hash碰撞，就会调用RequestParameter#equals判断是否是同一个对象
     * 最终就导致在{@link RequestParameter#equals}时报出空指针
     * @see springfox.documentation.swagger.readers.parameter.SwaggerExpandedParameterBuilder.apply
     *
     * 这里生成RequestParameterBuilder，为RequestParameterBuilder里的example赋值。这个赋值是导致空指针的罪魁祸首
     * @see springfox.documentation.swagger.readers.parameter.SwaggerExpandedParameterBuilder.fromApiModelProperty
     *
     * @see springfox.documentation.spring.web.readers.operation.OperationParameterReader.shouldExpand
     *
     * @see springfox.documentation.swagger.readers.parameter.SwaggerExpandedParameterBuilder.apply
     * @see springfox.documentation.swagger.readers.parameter.SwaggerExpandedParameterBuilder.apply
     */
    @ApiModelProperty
    lateinit var field1: String
}