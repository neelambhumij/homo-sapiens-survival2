package com.ioco.saviour.homosapienssurvival.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@EnableSwagger2
@Configuration
public class SwaggerConfig {

    @Bean
    public Docket docket() {

        return new Docket(DocumentationType.SWAGGER_2).select()
                .apis(RequestHandlerSelectors.basePackage("com.ioco.saviour.homosapienssurvival")).paths(PathSelectors.any()).build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfo("SURVIVOR DATA", "HOMO SAPIENS SURVIVAL API", "1.0", "",
                new Contact("IOCO", "", "reply.homosapiensurvival2050@gmailcom"), "", "", new ArrayList<>());
    }
}
