package com.myapp.clientservice;

import static com.myapp.clientservice.hoverfly.HoverflyStubs.SIMULATION_SOURCE;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.myapp.clientservice.config.IntegrationTestConfig;
import com.netflix.discovery.EurekaClient;
import io.specto.hoverfly.junit.rule.HoverflyRule;
import org.junit.ClassRule;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

/**
 * Integration tests for Clientservice-App with Hoverfly stubbing.
 *
 * @author Ivan_Semenov
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("integration-test")
@ContextConfiguration(classes = IntegrationTestConfig.class)
public class ClientApplicationIntegrationTest {

    @MockBean
    private EurekaClient eurekaClient;
    
    @ClassRule
    public static final HoverflyRule hoverflyRule = HoverflyRule
            .inSimulationMode(SIMULATION_SOURCE).printSimulationData();

    @Autowired
    private MockMvc mockMvc;
    
    @Test
    public void testGetTopAuthorsV1() throws Exception {
        mockMvc.perform(get("/client-app/get-top-authors-v1")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Author"));
    }

    @Test
    public void testGetFilteredAuthorsV1() throws Exception {
        mockMvc.perform(get("/client-app/get-filtered-authors-v1")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Author"));
    }

    @Test
    @Ignore //TODO investigate test failure
    public void testGetTopAuthorsV2() throws Exception {
        mockMvc.perform(get("/client-app/get-top-authors-v2")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Author"));
    }

    @Test
    @Ignore //TODO investigate test failure
    public void testGetFilteredAuthorsV2() throws Exception {
        mockMvc.perform(get("/client-app/get-filtered-authors-v2")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Author"));
    }

}