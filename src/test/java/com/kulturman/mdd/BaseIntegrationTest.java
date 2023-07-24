package com.kulturman.mdd;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.database.rider.core.api.configuration.DBUnit;
import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.spring.api.DBRider;
import com.kulturman.mdd.services.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;


@DBUnit(caseSensitiveTableNames = true)
@DBRider
@DataSet(value = "data/data.json")
@SpringBootTest
@AutoConfigureMockMvc
public class BaseIntegrationTest {

    @Autowired
    public JwtService jwtService;

    @Autowired
    public MockMvc mockMvc;

    @Autowired
    public ObjectMapper objectMapper;

    String DEFAULT_USER = "itachi@konoha.com";

    public String getToken(String email) {
        return jwtService.generateToken(email);
    }

    public MockHttpServletRequestBuilder authenticatedGet(String url, String email) {
        return get(url).header("Authorization", "Bearer " + jwtService.generateToken(email));
    }

    public MockHttpServletRequestBuilder authenticatedGet(String url) {
        return authenticatedGet(url, DEFAULT_USER);
    }

    public MockHttpServletRequestBuilder authenticatedPost(String url) {
        return authenticatedPost(url, DEFAULT_USER);
    }

    public MockHttpServletRequestBuilder authenticatedPost(String url, String email) {
        return post(url)
            .header("Authorization", "Bearer " + jwtService.generateToken(email))
            .contentType(MediaType.APPLICATION_JSON);
    }
}
