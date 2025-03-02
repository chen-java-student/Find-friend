package com.cyx.findfriend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;
/**
 * 自定义Swagger 接口文档的配置
 * @author: cyx
 */
@Configuration // 配置类
@EnableSwagger2WebMvc // 开启 swagger2 的自动配置
@Profile({"dev", "test"})//版本控制访问
public class SwaggerConfig {
        @Bean(value = "defaultApi2")
        public Docket defaultApi2() {
            return new Docket(DocumentationType.SWAGGER_2)
                    .apiInfo(apiInfo())
                    .select()
                    //扫描路径，获取controller层的接口
                    .apis(RequestHandlerSelectors.basePackage("com.cyx.findfriend.controller"))
                    .paths(PathSelectors.any())
                    .build();
        }

        public ApiInfo apiInfo(){
            return new ApiInfoBuilder()
                    //标题
                    .title("用户中心")
                    //简介
                    .description("接口文档")
                    .termsOfServiceUrl("www.chenyuxuan.fun")
                    //作者、网址http:localhost:8088/doc.html(这里注意端口号要与项目一致，如果你的端口号后面还加了前缀，就需要把前缀加上)、邮箱
                    .contact(new Contact("chenyuxuan","http:localhost:8080","598287339@qq.com"))
                    //版本
                    .version("1.0")
                    .build();
        }

}


