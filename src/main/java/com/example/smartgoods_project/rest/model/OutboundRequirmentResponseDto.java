package com.example.smartgoods_project.rest.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
public class OutboundRequirmentResponseDto {

    @NotBlank
    @NonNull
    String id;


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
    String isRuppScheme;

}
