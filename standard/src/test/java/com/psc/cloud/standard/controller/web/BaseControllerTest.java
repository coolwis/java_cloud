package com.psc.cloud.standard.controller.web;

import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
@SpringBootTest
@ActiveProfiles({"dev", "db-h2"})
@AutoConfigureMockMvc

class BaseControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    public void getIndex() throws Exception {
        String data ="GET";
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("data", data);
        val ok = HttpStatus.OK;
        MvcResult mvcResult=mockMvc.perform(get("/").params(params)).andDo(print())
                .andExpect(status().is(ok.value()))
                .andReturn()
        ;
        String content  = mvcResult.getResponse().getContentAsString();
        Assertions.assertThat(content).contains(data);
    }


    @Test
    public void postIndex() throws Exception {
        String data ="POST";
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("data", data);
        val ok = HttpStatus.OK;
        MvcResult mvcResult=mockMvc.perform(post("/").params(params)).andDo(print())
                .andExpect(status().is(ok.value()))
                .andReturn()
                ;
        String content  = mvcResult.getResponse().getContentAsString();
        Assertions.assertThat(content).contains(data);

    }

}