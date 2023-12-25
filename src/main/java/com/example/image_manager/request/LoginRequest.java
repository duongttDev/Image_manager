package com.example.image_manager.request;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LoginRequest {

    @NotBlank(message = "Username bắt buộc")
    @Size(min = 6, max = 10, message = "Username bắt buộc từ 6 đến 10 ký tự")
    private String username;

    @NotBlank(message = "password bắt buộc")
    @Size(min = 6, max = 10, message = "password bắt buộc từ 6 đến 10 ký tự")
    private String password;

}
