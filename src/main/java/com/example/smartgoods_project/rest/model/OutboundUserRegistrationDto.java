package com.example.smartgoods.rest.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OutboundUserRegistrationDto {

    Long id;
    String username;
    String firstName;
    String lastName;
}
