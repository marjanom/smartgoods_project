package com.example.smartgoods.rest.model;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Data
@Builder
public class InboundUserRegistrationDto {

    @NotBlank
    @NotBlank
    String username;

    @NotBlank
    @NotBlank
    String firstName;


    @NotBlank
    @NotBlank
    String lastName;

    @NotBlank
    @NotEmpty
    String password;
}