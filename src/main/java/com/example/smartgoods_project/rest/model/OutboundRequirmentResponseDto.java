package com.example.smartgoods_project.rest.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@Builder
public class OutboundRequirmentResponseDto {

    @NotBlank
    @NonNull
    Long id;


    @NotBlank
    @NonNull
    String username;

    @NotBlank
    @NonNull
    String projectName;


    @NotBlank
    @NonNull
    String requirement;

    @NotBlank
    @NonNull
    boolean isRuppScheme;

}
