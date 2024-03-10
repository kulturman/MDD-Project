package com.kulturman.mdd;

import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
public class TestContainer {
    public static MySQLContainer<?> mySQLContainer = new MySQLContainer<>("mysql:5.7")
        .withDatabaseName("mdd")
        .withPassword("mdd")
        .withUsername("mdd");

    static {
        mySQLContainer.start();
    }

    @DynamicPropertySource
    static void databaseProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", mySQLContainer::getJdbcUrl);
        registry.add("spring.datasource.username", mySQLContainer::getUsername);
        registry.add("spring.datasource.password", mySQLContainer::getPassword);
    }
}

