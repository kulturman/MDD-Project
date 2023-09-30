package com.kulturman.mdd.dtos.requests;

import javax.validation.constraints.NotEmpty;

public record CreateCommentRequest(@NotEmpty String content) {
}
