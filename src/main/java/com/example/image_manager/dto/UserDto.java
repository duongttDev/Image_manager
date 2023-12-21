package com.example.image_manager.dto;


import lombok.Data;

import java.util.Date;

@Data
public class UserDto {

    private Long id;

    private String username;

    private String password;

    private String name;

    private String phone;

    private String email;

    private String role;

    private String token;

    private Date expireDateToken;

}
