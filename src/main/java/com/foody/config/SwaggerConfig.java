package com.foody.config;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;

import java.util.ArrayList;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

//How to add token key in Swagger // how to add token key in swagger spring boot
//https://javadeveloperzone.com/spring-boot/spring-swagger-add-static-header-to-all-rest-service/
@Configuration
@EnableSwagger2
public class SwaggerConfig {

	 @Bean
    public Docket api() {
		 //Adding Header
	        ParameterBuilder aParameterBuilder = new ParameterBuilder();
	        aParameterBuilder.name("Authorization")                 // name of header
	                         .modelRef(new ModelRef("string"))
	                         .parameterType("header")               // type - header
	                         .defaultValue("Basic em9uZTpteXBhc3N3b3Jk")        // based64 of - zone:mypassword
	                         .required(true)                // for compulsory
	                         .build();
	        java.util.List<Parameter> aParameters = new ArrayList<>();
	        aParameters.add(aParameterBuilder.build());  
	        
        return new Docket(DocumentationType.SWAGGER_2).select()
            .apis(RequestHandlerSelectors
                .basePackage("com.foody.controller"))
            .paths(PathSelectors.regex("/.*"))
            .build().pathMapping("")
            .globalOperationParameters(aParameters)
            .apiInfo(apiEndPointsInfo());
    }
 
    private ApiInfo apiEndPointsInfo() {
        return new ApiInfoBuilder().title("Spring Boot REST API")
            .description("BEATIFULL HOUSE REST API")
            .contact(new Contact("Trinh Vo (LEO)", "vovantrinhdhbkdn@gmail.com", "vovantrinhdhbkdn@gmail.com"))
            .license("Apache 2.0")
            .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0")
            .version("1.0.0")
            .build();
    }
    
    
    @SuppressWarnings("unused")
	private ApiKey apiKey() {
	    return new ApiKey("authkey", "Authorization", "header");
	}

    // Only select apis that matches the given Predicates.
    @SuppressWarnings("unused")
	private Predicate<String> paths() {
    	// Match all paths except /error
        return Predicates.and(
        	PathSelectors.regex("/.*"), 
        	Predicates.not(PathSelectors.regex("/error.*"))
        );
    }
}
