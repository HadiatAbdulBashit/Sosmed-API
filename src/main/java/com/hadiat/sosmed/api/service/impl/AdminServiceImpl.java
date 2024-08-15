package com.hadiat.sosmed.api.service.impl;

import com.hadiat.sosmed.api.model.User;
import com.hadiat.sosmed.api.repository.UserRepository;
import com.hadiat.sosmed.api.service.AdminService;
import com.hadiat.sosmed.api.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {
    private final UserRepository UserRepository;
    private final AuthenticationService authenticationService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Page<User> findAllUser(Pageable pageable) {
        return UserRepository.findAll(pageable);
    }

    @Override
    public User findUserById(Integer id) {
        return UserRepository.findById(id).orElseThrow(() -> new RuntimeException("User Not Found"));
    }

    @Override
    public void deleteById(Integer id) {
        UserRepository.deleteById(id);
    }
}
