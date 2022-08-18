package com.yurii.financeanalytics.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yurii.financeanalytics.entity.payload.AuthRequest;
import com.yurii.financeanalytics.entity.payload.AuthResponse;
import com.yurii.financeanalytics.service.AuthService;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

@WebMvcTest(value = AuthController.class, useDefaultFilters = false, includeFilters = {
        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = AuthController.class)})
@AutoConfigureMockMvc(addFilters = false)
class AuthControllerTest {
    
    @Autowired
    private MockMvc mvc;
    
    @MockBean
    private AuthService authService;
    
    private ObjectMapper mapper;

    @Test
    void whenLogin_thenReturnStatus200() throws JsonProcessingException, Exception {
        mapper = new ObjectMapper();
        AuthRequest request = new AuthRequest("login", "password");
        AuthResponse expectedResponse = new AuthResponse("token", "Bearer");
        given(authService.login(Mockito.any(AuthRequest.class))).willReturn(expectedResponse);
        ResultActions result = mvc.perform(post("/api/analytics/auth/signin")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(request)))
        .andExpect(status().isOk())
        .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
        
        String resultString = result.andReturn().getResponse().getContentAsString();
        
        assertEquals(mapper.writeValueAsString(expectedResponse), resultString);
        verify(authService).login(request);
    }
    
    @AfterEach
    void tearDown() {
        verifyNoMoreInteractions(authService);
    }

}
