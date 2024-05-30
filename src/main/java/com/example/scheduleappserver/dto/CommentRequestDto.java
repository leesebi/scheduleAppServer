package com.example.scheduleappserver.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class CommentRequestDto {
    @JsonProperty("user_id")
    private String userId;
    @NotBlank
    private String content;
}
