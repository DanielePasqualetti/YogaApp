package com.example.YogaApp.security.service;

import com.example.YogaApp.security.payload.LoginDto;
import com.example.YogaApp.security.payload.RegisterDto;

public interface AuthService {
    
	String login(LoginDto loginDto);
    String register(RegisterDto registerDto);
    
}
