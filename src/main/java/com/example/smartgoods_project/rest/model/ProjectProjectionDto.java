package com.example.smartgoods_project.rest.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@Builder
public class ProjectProjectionDto {
    @NotBlank
    @NotNull
    Long id;
    @NotBlank
    @NotNull
    String projectName;
    @NotBlank
    @NotNull
    Long userId;
}
