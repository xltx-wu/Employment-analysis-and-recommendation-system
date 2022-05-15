package indi.xltx.earsystemserver.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class RecruitmentInfoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void start() {
        System.out.println("-----------------------开始---------------------------");
    }

    @AfterEach
    void end() {
        System.out.println("-----------------------结束---------------------------");
    }

    @Test
    void testGetCityList() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/jobinfo/getcitylist")
                .accept(MediaType.APPLICATION_JSON_VALUE);
        mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void testGetIndustryList() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/jobinfo/getindustrylist");
        mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void testGetInfoByKeyWord() throws Exception {
        String jsonString = "{\"city\":\"%s\",\"workTime\":%s,\"industry\":\"%s\",\"minSalary\":%s,\"maxSalary\":%s,\"offset\":%s,\"rows\":%s}";
        jsonString = String.format(jsonString, "北京市", "0", "制造业", "null", "null", "0", "5");
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/jobinfo/getbykey")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(jsonString);
        System.out.println(jsonString);
        mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andDo(MockMvcResultHandlers.print());
    }
}
