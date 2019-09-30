package com.myapp.clientservice;

import static com.myapp.clientservice.hoverfly.HoverflyStubs.SIMULATION_SOURCE;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.myapp.bookstore.feign.AuthorControllerFeign;
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
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;

/**
 * Integration tests for Clientservice-App with Hoverfly stubbing.
 *
 * @author Ivan_Semenov
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("integration-test")
@Ignore
public class ClientApplicationIntegrationTest {

    @MockBean
    private EurekaClient eurekaClient;
    
    @MockBean
    private AuthorControllerFeign authorControllerFeign;
    
    @ClassRule
    public static HoverflyRule hoverflyRule = HoverflyRule
            .inSimulationMode(SIMULATION_SOURCE).printSimulationData();

    @Autowired
    private MockMvc mockMvc;
    
    @Test
    public void testGetTopAuthorsV1() throws Exception {
        mockMvc.perform(get("/client-app/get-authors-v1")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Author"));
    }

    @Test
    public void testGetTopAuthorsV2() throws Exception {
        mockMvc.perform(get("/client-app/get-authors-v2")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Author"));
    }

}