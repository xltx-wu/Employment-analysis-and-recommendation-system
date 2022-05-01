package indi.xltx.earsystemserver.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class RegisterTest {

    @Autowired
    private MockMvc mockMvc;

    private MockHttpSession session;// 1.定义一个变量保存session

    @BeforeEach
    void start() {
        System.out.println("-----------------------开始---------------------------");

        session = new MockHttpSession();
    }

    @AfterEach
    void end() {
        System.out.println("-----------------------结束---------------------------");
    }

    @Test
    void testRegister() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.post("/api/captcha").session(session));

        String code = (String) session.getAttribute("simpleCaptcha");
        String jsonString = "{\"username\":\"%s\",\"password\":\"%s\",\"code\":\"%s\"}";
        jsonString = String.format(jsonString, "xiaoliang", "123456", code);
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonString)
                .session(session);
        System.out.println(jsonString);

        mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andDo(MockMvcResultHandlers.print());
    }
}
