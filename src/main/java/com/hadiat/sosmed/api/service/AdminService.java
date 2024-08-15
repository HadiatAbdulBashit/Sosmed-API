package com.hadiat.sosmed.api.service;

import com.hadiat.sosmed.api.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AdminService {
    Page<User> findAllUser(Pageable pageable);
    User findUserById(Integer id);
    void deleteById(Integer id);
}
