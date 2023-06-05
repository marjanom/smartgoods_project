package com.example.smartgoods_project.rest.model;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Data
@Builder
public class InboundUserChangePasswordDto {

    @NotBlank
    @NotEmpty
    private String oldPassword;
    @NotBlank
    @NotEmpty
    private String newPassword;
    @NotBlank
    @NotEmpty
    private String controlNewPassword;
}
