package com.example.smartgoods_project.rest.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Data
@Getter
@Setter
public class InboundUserRegistrationDto {

    @NotBlank
    @NotEmpty
    String username;
    @NotBlank
    @NotEmpty

    String firstName;
    @NotBlank
    @NotEmpty

    String lastName;
    @NotBlank
    @NotEmpty

    String password;

}