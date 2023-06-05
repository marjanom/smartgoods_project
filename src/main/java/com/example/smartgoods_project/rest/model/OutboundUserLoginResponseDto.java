package com.example.smartgoods_project.rest.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
public class OutboundUserLoginResponseDto {

    @NotBlank
    @NotNull
    String username;
}
