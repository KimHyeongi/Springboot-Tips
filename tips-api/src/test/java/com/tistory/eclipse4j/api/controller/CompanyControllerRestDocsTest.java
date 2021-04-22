package com.tistory.eclipse4j.api.controller;

import com.tistory.eclipse4j.api.company.controller.CompanyController;
import com.tistory.eclipse4j.api.company.data.CompanyInfo;
import com.tistory.eclipse4j.api.company.service.CompanyFinder;
import com.tistory.eclipse4j.api.restdocs.MockMvcDocBaseTest;
import com.tistory.eclipse4j.api.restdocs.RestDocsResponseFieldGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;

import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.headers.HeaderDocumentation.*;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.*;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@WebMvcTest(CompanyController.class)
public class CompanyControllerRestDocsTest extends MockMvcDocBaseTest {

    @MockBean
    private CompanyFinder companyFinder;


    @Test
    public void 회사() throws Exception {
        when(companyFinder.findCacheableById(any())).thenReturn(CompanyInfo.builder().id(1L).name("회사").build());
        this.mockMvc.perform(
                RestDocumentationRequestBuilders.get("/companies/{companyId}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("data").hasJsonPath())
                .andDo(
                        document(DOCUMENT,
                                preprocessRequest(prettyPrint()),
                                preprocessResponse(prettyPrint()),
                                requestHeaders(
                                ),
                                pathParameters(
                                        parameterWithName("companyId").description("회사ID")
                                ),
                                requestParameters(
                                ),
                                responseFields(
                                        RestDocsResponseFieldGenerator.make(CompanyInfo.class, "data")
                                )
                                ,
                                responseHeaders(headerWithName("Content-Type").description("The Content-Type of the payload, e.g. `application/hal+json`")
                                )));
        // @formatter:on
    }

}
