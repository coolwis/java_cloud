package com.psc.cloud.standard.controller.api;

import com.google.gson.Gson;
import com.psc.cloud.standard.controller.dto.ResponseDeptDto;
import lombok.extern.slf4j.Slf4j;
//import org.junit.jupiter.api.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.Order;
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
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

class DeptApiControllerTest {

    @Autowired
    MockMvc mockMvc;

    private String version="v1";



    @Test
    @Order(1)
    void A001_deptInsert() throws Exception {
        MultiValueMap<String, String> params= new LinkedMultiValueMap<>();
        params.add("deptno", "10");
        params.add("dname", "accounting");
        params.add("loc", "new york");
        MvcResult result= mockMvc.perform(post("/api/"+version+"/dept").params(params))
                .andDo(print())
                .andExpect(status().is(HttpStatus.OK.value()))
                .andReturn();
        String content  = result.getResponse().getContentAsString();
        Gson gson=new Gson();
        ResponseDeptDto responseDeptDto=gson.fromJson(content, ResponseDeptDto.class);

        Assertions.assertThat(responseDeptDto.getDeptno()).isEqualTo(10);

    }


}