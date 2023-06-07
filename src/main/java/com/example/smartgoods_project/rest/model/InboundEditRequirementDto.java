package com.example.smartgoods_project.rest.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
public class InboundEditRequirementDto {
    @NotBlank
    @NonNull
    String requirement;
}
