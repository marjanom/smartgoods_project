package com.example.smartgoods_project.rest.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InboundUserLoginDto {

    @NotBlank
    @NotEmpty
    @JsonProperty("username")
    private String username;
    @NotBlank
    @NotEmpty
    @JsonProperty("password")
    private String password;

}
