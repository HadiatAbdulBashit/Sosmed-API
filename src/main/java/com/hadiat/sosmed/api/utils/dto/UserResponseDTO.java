package com.hadiat.sosmed.api.utils.dto;

import com.hadiat.sosmed.api.model.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDTO {
    private Integer id;
    private String username;
    private String email;
    private Role role;
    private LocalDateTime createdAt;
}
