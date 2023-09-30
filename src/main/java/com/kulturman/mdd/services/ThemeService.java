package com.kulturman.mdd.services;

import com.kulturman.mdd.dtos.responses.ThemeResponse;
import com.kulturman.mdd.entities.User;
import com.kulturman.mdd.exceptions.BadRequestException;
import com.kulturman.mdd.exceptions.NotFoundException;
import com.kulturman.mdd.repositories.ThemeRepository;
import com.kulturman.mdd.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ThemeService {
    private final ThemeRepository themeRepository;

    public List<ThemeResponse> getAll() {
        return ThemeResponse.mapFromEntities(themeRepository.findAll());
    }

    public void unsubscribe(long themeId) {
        var authenticatedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        themeRepository.unsubscribe(themeId, authenticatedUser.getId());
    }

    public void subscribe(long themeId) {
        var authenticatedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        var theme = themeRepository.getReferenceById(themeId);

        if (theme == null) {
            throw new NotFoundException("Invalid theme");
        }

        if (
            themeRepository.doesUserAlreadySubscribed(authenticatedUser.getId(), themeId) > 0
        ) {
            //Use specific exception, Http exception should not be here
            throw new BadRequestException("Already subscribed to this theme");
        }

        themeRepository.subscribe(themeId, authenticatedUser.getId());
    }
}
