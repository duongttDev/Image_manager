package com.example.image_manager.service;

import com.example.image_manager.request.LoginRequest;
import com.example.image_manager.request.RegisterRequest;
import com.example.image_manager.response.EntityCustomResponse;

public interface AuthService {

    EntityCustomResponse login(LoginRequest loginRequest);

    EntityCustomResponse register(RegisterRequest registerRequest);

}
