package com.ukefu.webim.config.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class ApiConfigure {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
        		.groupName("UCKeFu")
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.ukefu.webim.web.handler.api.rest"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("优客服")
                .description("UCKeFu 基于 JAVA 语言开发，是一个全渠道融合的客户支持服务平台，聚合企业内部多个客服渠道，帮助各种行业各种规模的企业建立完整客服体系。通过将邮件、短信、电话语音、WebIM 在线客服、微信、微博、H5 页面、APP 接口等多个渠道来源的客户服务请求与对话汇聚在一个管理平台，用统一的方式来响应和支撑客户服务。")
                .termsOfServiceUrl("http://www.ukewo.cn/")
                .contact("优客服")
                .version("2.5.0")
                .build();
    }

}
