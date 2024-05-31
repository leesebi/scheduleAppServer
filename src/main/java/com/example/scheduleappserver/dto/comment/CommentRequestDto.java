package com.example.scheduleappserver.dto.comment;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class CommentRequestDto {
    @JsonProperty("user_name")
    private String userName;
    @NotBlank
    private String content;
}
