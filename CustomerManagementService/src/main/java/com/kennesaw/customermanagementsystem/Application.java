package com.kennesaw.customermanagementsystem;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import com.google.common.base.Predicates;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@ComponentScan("com.kennesaw.customermanagementsystem")
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	@Bean
	public Docket swaggerApi() {
		
		return new Docket(DocumentationType.SWAGGER_2)
				.enable(true)
				.apiInfo(apiInfo())
				.select()
				.apis(Predicates.not(RequestHandlerSelectors.basePackage("org.springframework.boot")))
				.build();
	}
	
	private ApiInfo apiInfo() {
		Contact contact = new Contact("Sameeraja Malladi", "https://www.linkedin.com/in/malladi-sameeraja-70b677a4/", "");
		return new ApiInfoBuilder()
				.title("CustomerManagementSystem API")
				.description("SERVICE THAT MANAGES CUSTOMERS FOR BUSINESS")
				.contact(contact)
				.build();
	}

}
