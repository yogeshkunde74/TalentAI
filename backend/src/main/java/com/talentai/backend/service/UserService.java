package com.talentai.backend.service;

import java.util.List;
import java.util.Optional;

import com.talentai.backend.dto.RegisterRequest;
import com.talentai.backend.entity.User;

public interface UserService {

    User registerUser(RegisterRequest request);

    List<User> getAllUsers();

    Optional<User> getUserById(Long id);

    Optional<User> getUserByEmail(String email);

    void deleteUser(Long id);
}