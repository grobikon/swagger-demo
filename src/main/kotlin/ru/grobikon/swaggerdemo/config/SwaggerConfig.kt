package ru.grobikon.swaggerdemo.config

import io.swagger.v3.oas.models.security.SecurityScheme
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpHeaders.AUTHORIZATION
import org.springframework.web.bind.annotation.RestController
import ru.grobikon.swaggerdemo.constant.SwaggerConstant.API_DESCRIPTION
import ru.grobikon.swaggerdemo.constant.SwaggerConstant.API_TAG
import ru.grobikon.swaggerdemo.constant.SwaggerConstant.API_TITLE
import ru.grobikon.swaggerdemo.constant.SwaggerConstant.API_VERSION
import ru.grobikon.swaggerdemo.constant.SwaggerConstant.AUTHORIZATION_DESCRIPTION
import ru.grobikon.swaggerdemo.constant.SwaggerConstant.AUTHORIZATION_SCOPE
import ru.grobikon.swaggerdemo.constant.SwaggerConstant.CONTACT_EMAIL
import ru.grobikon.swaggerdemo.constant.SwaggerConstant.CONTACT_NAME
import ru.grobikon.swaggerdemo.constant.SwaggerConstant.CONTACT_URL
import ru.grobikon.swaggerdemo.constant.SwaggerConstant.LICENSE
import ru.grobikon.swaggerdemo.constant.SwaggerConstant.LICENSE_URL
import ru.grobikon.swaggerdemo.constant.SwaggerConstant.SECURE_PATH
import ru.grobikon.swaggerdemo.constant.SwaggerConstant.SECURITY_REFERENCE
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.builders.PathSelectors.regex
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.service.*
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spi.service.contexts.SecurityContext
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger.web.*


@Configuration
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
            .forCodeGeneration(true)
            .securityContexts(listOf(securityContext()))
            .securitySchemes(listOf(apiKey()))
            .select()
            .apis(RequestHandlerSelectors.withClassAnnotation(RestController::class.java))
            .paths(regex(SECURE_PATH))
            .build()
            .tags(Tag(API_TAG, "API Books"))
    }

    private fun apiInfo(): ApiInfo {
        return ApiInfoBuilder()
            .title(API_TITLE)
            .description(API_DESCRIPTION)
            .termsOfServiceUrl("https://github.com/grobikon/swagger-demo")
            .contact(contact())
            .license(LICENSE)
            .licenseUrl(LICENSE_URL)
            .version(API_VERSION)
            .build()
    }

    private fun contact(): Contact {
        return Contact(CONTACT_NAME, CONTACT_URL, CONTACT_EMAIL)
    }


    private fun apiKey(): ApiKey {
        return ApiKey(
            SECURITY_REFERENCE,
            AUTHORIZATION,
            SecurityScheme.In.HEADER.name
        )
    }

    private fun securityContext(): SecurityContext {
        return SecurityContext.builder().securityReferences(securityReference()).build()
    }

    private fun securityReference(): List<SecurityReference?> {
        val authorizationScope = arrayOf(AuthorizationScope(AUTHORIZATION_SCOPE, AUTHORIZATION_DESCRIPTION))
        return listOf(SecurityReference(SECURITY_REFERENCE, authorizationScope))
    }

    @Bean
    fun security(): SecurityConfiguration? {
        return SecurityConfigurationBuilder.builder()
            .clientId("test-app-client-id")
            .clientSecret("test-app-client-secret")
            .realm("test-app-realm")
            .appName("test-app")
            .scopeSeparator(",")
            .additionalQueryStringParams(null)
            .useBasicAuthenticationWithAccessCodeGrant(true)
            .enableCsrfSupport(false)
            .build()
    }

    @Bean
    fun uiConfig(): UiConfiguration? {
        return UiConfigurationBuilder.builder()
            .deepLinking(true)
            .displayOperationId(false)
            .defaultModelsExpandDepth(1)
            .defaultModelRendering(ModelRendering.MODEL)
            .displayRequestDuration(true)
            .docExpansion(DocExpansion.LIST)
            .filter(false)
            .maxDisplayedTags(null)
            .operationsSorter(OperationsSorter.ALPHA)
            .showExtensions(false)
            .showCommonExtensions(true)
            .tagsSorter(TagsSorter.ALPHA)
            .supportedSubmitMethods(UiConfiguration.Constants.DEFAULT_SUBMIT_METHODS)
            .validatorUrl(null)
            .build()
    }
}