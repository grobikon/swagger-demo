package ru.grobikon.swaggerdemo.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.builders.PathSelectors.regex
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.service.ApiInfo
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2


@Configuration
@EnableSwagger2
class SwaggerConfig {

    /**
     * Специальные настройки Swagger
     * Docket(DocumentationType.SWAGGER_2) тип документации SWAGGER_2,
     * apis базовый пакет, где находиться controller
     * paths пути которые будут отображаться (PathSelectors.any()) все endPoint ы которые есть в проекте
     */
    @Bean
    fun postsApi(): Docket {
        return Docket(DocumentationType.SWAGGER_2)
            .groupName("Grobikon")
            .apiInfo(apiInfo())
            .select()
            //.apis(RequestHandlerSelectors.basePackage("ru.grobikon.grobikon.controller"))
            .paths(regex("/book.*"))
            .build()
    }

    private fun apiInfo(): ApiInfo {
        return ApiInfoBuilder().title("Book Service")
            .description("Образец документации, созданный с помощью SWAGGER 2 для API Book Rest")
            .termsOfServiceUrl("https://github.com/grobikon/swagger-demo")
            .license("Лицензия grobikon")
            .licenseUrl("https://github.com/grobikon/swagger-demo/blob/main/README.md").version("1.0").build()
    }
}