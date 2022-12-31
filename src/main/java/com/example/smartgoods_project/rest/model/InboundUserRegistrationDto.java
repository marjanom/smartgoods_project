package com.example.smartgoods_project.rest.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;
import org.mapstruct.ap.internal.util.IgnoreJRERequirement;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Data
public class InboundUserRegistrationDto {

    @NotBlank
    @NotEmpty
    String uuid;

}