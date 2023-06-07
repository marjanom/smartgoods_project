package com.example.smartgoods_project.rest.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@Builder
public class RequirementsProjectionDto {
    @NotBlank
    @NotNull
    Long id;
    @NotBlank
    @NotNull
    String isRuppScheme;
    @NotBlank
    @NotNull
    String requirement;


}
