package com.kulturman.mdd.controllers;

import com.kulturman.mdd.dtos.responses.ThemeResponse;
import com.kulturman.mdd.services.ThemeService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/themes")
@AllArgsConstructor
public class ThemeController {
    private final ThemeService themeService;

    @GetMapping()
    public ResponseEntity<List<ThemeResponse>> getAllThemes() {
        return ResponseEntity.ok(themeService.getAll());
    }

    @PostMapping("/{id}/subscribe")
    public ResponseEntity<?> unsubscribe(@PathVariable("id") long themeId) {
        themeService.unsubscribe(themeId);
        return ResponseEntity.ok().build();
    }
}
