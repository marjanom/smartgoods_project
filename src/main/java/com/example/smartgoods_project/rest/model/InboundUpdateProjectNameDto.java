package com.example.smartgoods_project.rest.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
public class InboundUpdateProjectNameDto {

    @NotBlank
    @NotEmpty
    String oldProjectName;
    @NotBlank
    @NotEmpty
    String newProjectName;
}
