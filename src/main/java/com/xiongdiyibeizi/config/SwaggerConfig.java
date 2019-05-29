package com.xiongdiyibeizi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.google.common.base.Predicates;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableWebMvc
@EnableSwagger2
public class SwaggerConfig {
	 //swagger2的配置
	 @Bean
	 public Docket createRestApi() {
	     return new Docket(DocumentationType.SWAGGER_2)
	             .apiInfo(apiInfo())
	             .enable(true)
	             .select()
	             //接口包的路径  
	             .apis(RequestHandlerSelectors.basePackage("com.xiongdiyibeizi.controller"))
	             .paths(PathSelectors.any())
	             .build();
	 }

	//构建 api文档的详细信息
 	private ApiInfo apiInfo() {
	     return new ApiInfoBuilder()
	             //页面标题
	             .title("武术项目接口文档")
	             //创建人 名称 网址 邮箱
	             .contact(new Contact("wushu", "", "2510165121@qq.com"))
	             //版本号
	             .version("1.0")
	             //描述
	             .description("API 测试")
	             .build();
 	}
}
