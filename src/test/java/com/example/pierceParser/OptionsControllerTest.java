package com.example.pierceParser;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class OptionsControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void getAllOptionsTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/options")
                        .header(HttpHeaders.ACCEPT_LANGUAGE, "pl_PL")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void getAllOptionsTest_noHeader() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/options")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void getOptionByCodeTest_success() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/options/9_10")
                        .header(HttpHeaders.ACCEPT_LANGUAGE, "pl_PL")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void getOptionByCodeTest_notFound() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/options/codeThatDoesNotExist")
                        .header(HttpHeaders.ACCEPT_LANGUAGE, "pl_PL")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void getOptionsByAttributeNameTest_success() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/options/byAttribute/helmetsize")
                        .header(HttpHeaders.ACCEPT_LANGUAGE, "pl_PL")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void getOptionsByAttributeNameTest_notFound() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/options/byAttribute/attributeThatDoesNotExist")
                        .header(HttpHeaders.ACCEPT_LANGUAGE, "pl_PL")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
}
