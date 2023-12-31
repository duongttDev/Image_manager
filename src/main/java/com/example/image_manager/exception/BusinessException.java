package com.example.image_manager.exception;


import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException {

  private int statusCode;

    public BusinessException(int statusCode, String message) {
        super(message);
        this.statusCode=statusCode;
    }
}
