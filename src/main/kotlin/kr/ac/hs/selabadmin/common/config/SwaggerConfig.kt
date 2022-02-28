package kr.ac.hs.selabadmin.common.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.ResponseEntity
import org.springframework.http.server.ServerHttpRequest
import org.springframework.http.server.ServerHttpResponse
import org.springframework.web.server.ServerWebExchange
import org.springframework.web.server.WebSession
import springfox.documentation.builders.PathSelectors
import springfox.documentation.service.ApiInfo
import springfox.documentation.service.Contact
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2
import java.util.*

@Configuration
@EnableSwagger2
class SwaggerConfig {

    private val PROJECT_NAME = "SELAB-ADMIN-API"
    private val GITHUB_URL = "https://github.com/selab-hs/selab-admin-api"
    private val DEVELOPER_EMAIL = "wrjssmjdhappy@gmail.com"
    private val API_TITLE = "SELAB_ADMIN_REST_API"
    private val API_DESCRIPTION = "Management REST API SERVICE"
    private val API_VERSION = "1.0"
    private val TERMS_OF_SERVICE_URL = "urn:tos"
    private val APACHE_LICENSE = "Apache 2.0"
    private val APACHE_LICENSE_URL = "http://www.apache.org/licenses/LICENSE-2.0"

    private val DEFAULT_CONTACT = Contact(
        PROJECT_NAME,
        GITHUB_URL,
        DEVELOPER_EMAIL
    )

    private val Default_API_INFO = ApiInfo(
        API_TITLE,
        API_DESCRIPTION,
        API_VERSION,
        TERMS_OF_SERVICE_URL,
        DEFAULT_CONTACT,
        APACHE_LICENSE,
        APACHE_LICENSE_URL,
        ArrayList()
    )

    @Bean
    fun api(): Docket? =
        Docket(DocumentationType.SWAGGER_2)
            .enable(true)
            .useDefaultResponseMessages(false)
            .ignoredParameterTypes(
                WebSession::class.java,
                ServerHttpRequest::class.java,
                ServerHttpResponse::class.java,
                ServerWebExchange::class.java
            )
            .apiInfo(Default_API_INFO)
            .genericModelSubstitutes(
                Optional::class.java,
                ResponseEntity::class.java
            )
            .select()
            .paths(PathSelectors.regex("/api/.*"))
            .build()
}