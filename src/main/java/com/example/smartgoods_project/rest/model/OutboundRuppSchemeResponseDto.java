package com.example.smartgoods_project.rest.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OutboundRuppSchemeResponseDto {
    @NotBlank
    @NotNull
    boolean isRuppScheme;
    @NotBlank
    @NotNull
    String hint;
}
