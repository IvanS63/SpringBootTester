package com.myapp.bookstore;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.myapp.bookstore.util.IntegrationTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

/**
 * Author book store application integration test.
 *
 * @author Ivan_Semenov
 */
@IntegrationTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@ActiveProfiles("integration-test") //will automatically use properties from application-integration-test.yml
public class BookStoreIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldReturnDefaultMessage() throws Exception {
        mockMvc.perform(get("/book-store/authors/top?from=1990-01-01&to=2120-01-01")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Chuck Palanik"))
                .andExpect(jsonPath("$[1].name").value("Stephen King"))
                .andExpect(jsonPath("$[2].name").value("Peter Straub"))
                .andExpect(jsonPath("$[3].name").value("F. Scott Fitzgerald"))
                .andExpect(jsonPath("$[4].name").value("George Orwell"));
    }
}
