package com.example.scheduleappserver.dto;


import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CommonResponse<T> {
    private Integer statusCode;
    private String msg;
    private Object data;
}
