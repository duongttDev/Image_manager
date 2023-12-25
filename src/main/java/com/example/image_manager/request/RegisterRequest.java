package com.example.image_manager.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RegisterRequest {

    @NotBlank(message = "Username bắt buộc")
    @Size(min = 6, max = 10, message = "Username bắt buộc từ 6 đến 10 ký tự")
    private String username;

    @NotBlank(message = "password bắt buộc")
    @Size(min = 6, max = 10, message = "password bắt buộc từ 6 đến 10 ký tự")
    private String password;

    @NotBlank(message = "name bắt buộc")
    private String name;

    @NotBlank(message = "Phone bắt buộc")
    @Size(min = 10, max = 15, message = "số điện thoại phải từ 10 đến 15 kí tự")
    @Pattern(regexp = "^[0-9]+$", message = "số điện thoại phải là số")
    private String phone;

    @Email(message = "email bắt buộc")
    @Size(max = 255, message = "Email không thể chứa 255 kí tự")
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "email không đúng định dạng")
    private String email;

    private String role;

}
