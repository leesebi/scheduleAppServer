package com.example.scheduleappserver.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class CommentUpdateRequestDto {
    @JsonProperty("content")
    private String Content;
}
