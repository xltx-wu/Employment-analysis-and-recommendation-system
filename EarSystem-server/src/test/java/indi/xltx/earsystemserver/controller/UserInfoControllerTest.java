package indi.xltx.earsystemserver.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class UserInfoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testGetUserInfo() throws Exception {

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/user/getuserinfo");

        mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andReturn();
    }

    @Test
    void testUpdateUserInfo() {

    }
}
