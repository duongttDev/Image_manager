package com.example.image_manager.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Builder
@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class EntityCustomResponse {
   private int status;

    private String message;

    private int code;

    private Object data;

}
