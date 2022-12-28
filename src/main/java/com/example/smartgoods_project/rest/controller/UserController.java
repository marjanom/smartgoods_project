package com.example.smartgoods_project.rest.controller;

import com.example.smartgoods_project.exceptions.UserAlreadyExistsException;
import com.example.smartgoods_project.rest.model.InboundUserRegistrationDto;
import com.example.smartgoods_project.rest.model.OutboundUserRegistrationDto;
import com.example.smartgoods_project.rest.service.UserRestService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping("users")
@Tag(name = "Users", description = "Endpoints for managing users")
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class UserController {

    UserRestService userRestService;


    @Operation(summary = "Creates a user in the database.", tags = {"Users"}, responses = {@ApiResponse(description = "Created", responseCode = "201", content = @Content(mediaType = "application/json", schema = @Schema(implementation = OutboundUserRegistrationDto.class))), @ApiResponse(description = "User already Exists", responseCode = "409", content = @Content)})
    @PostMapping("/register")
    public ResponseEntity<OutboundUserRegistrationDto> register(@Valid @RequestBody InboundUserRegistrationDto inboundUserRegistrationDto) throws UserAlreadyExistsException{

        OutboundUserRegistrationDto outboundUserRegistrationDto = userRestService.createUser(inboundUserRegistrationDto);
        return new ResponseEntity<>(outboundUserRegistrationDto, HttpStatus.CREATED);

    }
}


























/*

@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping("users")
@Tag(name = "Users", description = "Endpoints for managing users")
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class UserController {
    UserRestService userRestService;
    @Operation(
            summary = "Creates a user in the database.",
            tags = {"Users"},
            responses = {
                    @ApiResponse(
                            description = "OK",
                            responseCode = "201",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = InboundUserRegistrationDto.class))
                    ),
                    @ApiResponse(description = "User already Exists", responseCode = "409", content = @Content)
            }

    )
    @PostMapping("/registration/{uuid}")
    public InboundUserRegistrationDto register(@Valid @RequestBody InboundUserRegistrationDto inboundUserRegistrationDto) throws UserAlreadyExistsException {
        return userRestService.createUser(inboundUserRegistrationDto);
    }



/*
    @PostMapping("/registration/{uuid}")
    public String registerUserByUuid(@PathVariable(value = "uuid") String uuid) {
        User newUser = new User(uuid);

        if (this.SmartGoodsUserRepo.save(newUser) != null) {
            return "Processed Request";
        } else if (this.SmartGoodsUserRepo.findById(uuid).isEmpty()) {
            return "Processed Request";
        }
        return "Processed Request";
    }*/



