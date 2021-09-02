package org.baichuan.example.spring.springfox

import com.fasterxml.classmate.types.ResolvedObjectType
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.oas.annotations.EnableOpenApi
import springfox.documentation.service.ApiInfo
import springfox.documentation.service.Contact
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket


/**
 * @author: tk (rivers.boat.snow@gmail.com)
 * @date: 2021/8/23
 */
@Configuration
@EnableOpenApi
open class SwaggerConfiguration {

    @Bean
    open fun paymentApi(): Docket? {
        val resolvedType = ResolvedObjectType.create(String::class.java, null, null, null)
        return Docket(DocumentationType.SWAGGER_2)
            .additionalModels(resolvedType)
            .groupName("main-1")
            .apiInfo(apiInfo())
            .select()
            .apis(RequestHandlerSelectors.basePackage("org.baichuan.example.spring.springfox"))
            .paths(PathSelectors.any())
            .build()
    }

    private fun apiInfo(): ApiInfo? {
        return ApiInfoBuilder()
            .title("payment service")
            .description("每记支付服务")
            .contact(Contact("mampod", "", "tangkun@mampod.com"))
            .version("0.0.1")
            .build()
    }
}