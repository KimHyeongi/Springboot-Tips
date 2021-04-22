package com.tistory.eclipse4j.api.configuration;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import com.google.common.base.Predicates;

import org.springframework.web.servlet.resource.PathResourceResolver;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.DocExpansion;
import springfox.documentation.swagger.web.ModelRendering;
import springfox.documentation.swagger.web.OperationsSorter;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger.web.UiConfigurationBuilder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@EnableSpringDataWebSupport
@Configuration
public class ApiWebConfiguration extends WebMvcConfigurationSupport {

	@Value("${swagger.enabled:true}")
	private Boolean swaggerEnabled;

	private ApiInfo metaData() {
		// @formatter:off
		return new ApiInfoBuilder().title("API Server").description("API").version("1.0.0")
				.contact(new Contact("Grissom", "https://eclipse4j.tistory.com", "eclipse4j@gmail.com")).build();
		// @formatter:on
	}

	@Bean
	public Docket api() {
		// @formatter:off
		return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.any())
				.apis(Predicates.not(RequestHandlerSelectors.basePackage("org.springframework.boot")))
				.apis(Predicates.not(RequestHandlerSelectors.basePackage("org.springframework.cloud")))
				.paths(PathSelectors.any()).build().enable(swaggerEnabled)
				.directModelSubstitute(LocalDate.class, String.class)
				.directModelSubstitute(LocalTime.class, String.class).genericModelSubstitutes(ResponseEntity.class)
				.useDefaultResponseMessages(true).apiInfo(metaData());
		// @formatter:on
	}

	@Bean
	public UiConfiguration uiConfig() {
		return UiConfigurationBuilder.builder().displayOperationId(true).defaultModelExpandDepth(2)
				.defaultModelRendering(ModelRendering.MODEL).displayRequestDuration(true)
				.docExpansion(DocExpansion.NONE).operationsSorter(OperationsSorter.METHOD).showExtensions(true).build();
	}

	protected void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
		registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
		registry.addResourceHandler("/docs/**")
				.addResourceLocations("classpath:/public/", "classpath:/static/", "classpath:/resources/", "classpath:/META-INF/resources/")
				.resourceChain(true)
				.addResolver(new PathResourceResolver());
	}


}
