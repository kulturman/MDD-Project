package com.kulturman.mdd.controllers;

import com.kulturman.mdd.dtos.responses.ThemeResponse;
import com.kulturman.mdd.services.ThemeService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class ThemeController {
    private final ThemeService themeService;

    @GetMapping("/themes")
    public ResponseEntity<List<ThemeResponse>> getAllThemes() {
        return ResponseEntity.ok(themeService.getAll());
    }
}
