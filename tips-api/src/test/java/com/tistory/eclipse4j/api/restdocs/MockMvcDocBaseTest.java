package com.tistory.eclipse4j.api.restdocs;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessRequest;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@ExtendWith({RestDocumentationExtension.class, SpringExtension.class})
public abstract class MockMvcDocBaseTest {
	
	public static final String DOCUMENT = "{class-name}/{method-name}";
	protected MockMvc mockMvc;
	
	@BeforeEach
	public void setUp(WebApplicationContext webApplicationContext, RestDocumentationContextProvider restDocumentation) {
		log.info("@BeforeEach");
		this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
				.apply(
						documentationConfiguration(restDocumentation)
						.uris()
						.withHost("eclipse4j.tistory.com")
						.withPort(80)
						.withScheme("http")
				)
				.alwaysDo( 
						document(DOCUMENT, preprocessRequest(prettyPrint()), preprocessResponse(prettyPrint()))
				)
				.build();
	}
}
