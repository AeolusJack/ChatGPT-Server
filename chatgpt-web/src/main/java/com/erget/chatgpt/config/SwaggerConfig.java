package com.erget.chatgpt.config;


import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.RequestParameterBuilder;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ParameterType;
import springfox.documentation.service.RequestParameter;
import springfox.documentation.service.Response;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;
import java.util.List;

/**
 * Swagger 配置
 */
@Configuration
@Profile({"local", "dev", "DEV", "fat", "FAT","UAT"})
@EnableOpenApi
public class SwaggerConfig implements WebMvcConfigurer {
    @Bean
    public Docket createRestApi() {
        List<Response> responseList = new ArrayList<>();
        return new Docket(DocumentationType.OAS_30)
                .globalRequestParameters(requestParameterBuilderList())
                .apiInfo(apiInfo())
                .select()
                // 扫描所有有注解的api
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
//                .apis(RequestHandlerSelectors.basePackage("com.tmzh.saas"))
                .build();
    }

    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("线索服务接口文档")
                .description("线索服务接口文档：do something表示测试接口")
                .version("3.0.0")
                .build();
    }

    private ArrayList<RequestParameter> requestParameterBuilderList(){
        return  new ArrayList<>() ;

    }
}