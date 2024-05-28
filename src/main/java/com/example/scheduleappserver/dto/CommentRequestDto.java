package com.example.scheduleappserver.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class CommentRequestDto {
    @JsonProperty("user_id")
    private String userId;
    private String content;
}
