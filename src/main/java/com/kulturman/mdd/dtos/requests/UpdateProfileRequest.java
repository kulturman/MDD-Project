package com.kulturman.mdd.dtos.requests;

import javax.validation.constraints.NotNull;

public record UpdateProfileRequest(@NotNull String email, @NotNull String username) {
}
