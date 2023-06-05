package com.example.smartgoods_project.rest.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
public class OutboundUserRegistrationResponseDto {

    @NotBlank
    @NotNull
    String username;
    @NotBlank
    @NotNull
    String firstName;
    @NotBlank
    @NotNull
    String lastName;
}
