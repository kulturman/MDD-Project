package com.kulturman.mdd.services;

import com.kulturman.mdd.dtos.responses.ThemeResponse;
import com.kulturman.mdd.entities.Theme;
import com.kulturman.mdd.repositories.ThemeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ThemeService {
    private final ThemeRepository themeRepository;
    public List<ThemeResponse> getAll() {
        return ThemeResponse.mapFromEntities(themeRepository.findAll());
    }
}
