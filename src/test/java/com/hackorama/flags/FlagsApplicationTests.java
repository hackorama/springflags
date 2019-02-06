package com.hackorama.flags;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * Spring boot unit/integration tests for Flags Application
 *
 * @author Kishan Thomas (kishan.thomas@gmail.com)
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class FlagsApplicationTests {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Test
    public void doingGetRequests_verifyExpectedResponseBody() throws Exception {
        mockMvc.perform(get("/flags/USA")).andExpect(jsonPath("$[0].name").value("USA"));
        mockMvc.perform(get("/flags/USA")).andExpect(jsonPath("$[0].flag").value("🇺🇸"));
        mockMvc.perform(get("/flags/America")).andExpect(jsonPath("$[0].name").value("USA"));
        mockMvc.perform(get("/flags/America")).andExpect(jsonPath("$[0].flag").value("🇺🇸"));
        mockMvc.perform(get("/flags")).andExpect(jsonPath("$[0].name").value("Papua New Guinea"));
        mockMvc.perform(get("/flags")).andExpect(jsonPath("$[0].flag").value("🇵🇬"));
    }

    @Test
    public void doingInvalidGetRequests_verifyExpectedResponseStatus() throws Exception {
        mockMvc.perform(get("/flags/unknown")).andExpect(status().isBadRequest());
        mockMvc.perform(get("/unknown")).andExpect(status().isNotFound());
    }

    @Test
    public void doingValidGetRequests_verifyExpectedResponseStatus() throws Exception {
        mockMvc.perform(get("/flags")).andExpect(status().isOk());
        mockMvc.perform(get("/flags/USA")).andExpect(status().isOk());
        mockMvc.perform(get("/flags/America")).andExpect(status().isOk());
    }

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }
}
