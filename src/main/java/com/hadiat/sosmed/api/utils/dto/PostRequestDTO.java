package com.hadiat.sosmed.api.utils.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hadiat.sosmed.api.model.enums.PostStatus;
import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostRequestDTO {
    private String title;
    private String description;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dueDate;
    @Nullable
    private PostStatus status;
}
