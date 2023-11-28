package com.example.image_manager.dto;


import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BaseDto {

    private Long id;

    private LocalDateTime createAt;

    private LocalDateTime updateAt;

    private int createBy;

    private int updateBy;
}
