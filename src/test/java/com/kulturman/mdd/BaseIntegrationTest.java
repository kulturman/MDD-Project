package com.kulturman.mdd;

import com.github.database.rider.core.api.configuration.DBUnit;
import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.spring.api.DBRider;
import com.kulturman.mdd.services.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;


@DBUnit(caseSensitiveTableNames = true)
@DBRider
@DataSet(value = "data/users.json")
@SpringBootTest
@AutoConfigureMockMvc
public class BaseIntegrationTest {

    @Autowired
    public JwtService jwtService;

    @Autowired
    public MockMvc mockMvc;

    public String getToken(String email) {
        return jwtService.generateToken(email);
    }

    public MockHttpServletRequestBuilder authenticatedGet(String url, String email) {
        return get(url).header("Authorization", "Bearer " + jwtService.generateToken("kakashi@konoha.com"));
    }
}
