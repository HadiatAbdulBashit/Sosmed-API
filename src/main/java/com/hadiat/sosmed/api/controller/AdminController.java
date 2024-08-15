package com.hadiat.sosmed.api.controller;

import com.hadiat.sosmed.api.model.User;
import com.hadiat.sosmed.api.service.AdminService;
import com.hadiat.sosmed.api.utils.dto.UserResponseDTO;
import com.hadiat.sosmed.api.utils.responseWrapper.PaginationResponse;
import com.hadiat.sosmed.api.utils.responseWrapper.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService AdminService;

    @GetMapping("/users")
    public ResponseEntity<?> findAllUsers(@PageableDefault Pageable pageable) {
        Page<User> userPage = AdminService.findAllUser(pageable);
        Page<UserResponseDTO> userResponseDTOPage = userPage.map(user -> UserResponseDTO.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .role(user.getRole())
                .createdAt(user.getCreatedAt())
                .build());
        return Response.renderJSON(
                new PaginationResponse<>(userResponseDTOPage),
                "Success",
                HttpStatus.OK
        );
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<?> findUserById(@PathVariable Integer id) {
        User user = AdminService.findUserById(id);
        return Response.renderJSON(
                UserResponseDTO.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .role(user.getRole())
                .createdAt(user.getCreatedAt())
                .build(),
                "Success",
                HttpStatus.OK);
    }
}
