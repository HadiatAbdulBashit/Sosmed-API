package com.hadiat.sosmed.api.utils.responseWrapper;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WebResponseError<T> {
    private String message;
    private List<T> errors;
}