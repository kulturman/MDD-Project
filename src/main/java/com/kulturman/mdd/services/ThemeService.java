package com.kulturman.mdd.services;

import com.kulturman.mdd.dtos.responses.ThemeResponse;
import com.kulturman.mdd.entities.User;
import com.kulturman.mdd.repositories.ThemeRepository;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ThemeService {
    private final ThemeRepository themeRepository;
    private final JdbcTemplate jdbcTemplate;

    public List<ThemeResponse> getAll() {
        return ThemeResponse.mapFromEntities(themeRepository.findAll());
    }

    public void unsubscribe(long themeId) {
        var authenticatedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        jdbcTemplate.execute(
            String.format("DELETE FROM subscriptions WHERE user_id = %d AND theme_id = %d", authenticatedUser.getId(), themeId)
        );
    }
}
