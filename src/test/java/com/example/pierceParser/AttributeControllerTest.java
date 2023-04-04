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
public class AttributeControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void getAllAttributesTest_success() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/attributes")
                        .header(HttpHeaders.ACCEPT_LANGUAGE, "pl_PL")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void getAllAttributesTest_noHeader() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/attributes")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void getAttributeByCodeTest_success() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/attributes/helmetsize")
                        .header(HttpHeaders.ACCEPT_LANGUAGE, "pl_PL")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void getAttributeByCodeTest_notFound() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/attributes/attributeThatDoesNotExist")
                        .header(HttpHeaders.ACCEPT_LANGUAGE, "pl_PL")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}
