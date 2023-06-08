package com.example.smartgoods_project.rest.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@Builder
public class OutboundEditRequirementDto {
    @NotBlank
    @NonNull
    Long id;
    @NotBlank
    @NonNull
    String requirement;
}
