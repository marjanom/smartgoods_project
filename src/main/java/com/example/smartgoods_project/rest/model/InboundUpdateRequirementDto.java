package com.example.smartgoods_project.rest.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRawValue;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
public class InboundUpdateRequirementDto {
    @NotBlank
    @NotEmpty
    String requirement;
    @NotBlank
    @NotEmpty
    String projectName;

}
