package com.talentai.backend.service;

import com.talentai.backend.dto.LoginRequest;
import com.talentai.backend.dto.LoginResponse;

public interface AuthService {

    LoginResponse login(LoginRequest request);

}