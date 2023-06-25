package com.example.smartgoods_project.rest.controller;

import com.example.smartgoods_project.entity.models.User;
import com.example.smartgoods_project.exceptions.*;
import com.example.smartgoods_project.rest.model.*;
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

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.UUID;


@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping("api/v2/users")
@Tag(name = "User", description = "Endpoints for managing user")
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class UserController {

    UserRestService userRestService;
    final static String sessionIdName = "X-SESSION-ID";

    /**
     * Registers the user in the database.
     *
     * @return Returns HTTP Created.
     * @throws UserAlreadyExistsException In case the username exist`s.
     */

    @Operation(summary = "Creates a user in the database.", tags = {"User"}, responses = {@ApiResponse(description = "Created", responseCode = "201", content = @Content(mediaType = "application/json", schema = @Schema(implementation = OutboundUserRegistrationResponseDto.class))), @ApiResponse(description = "The username is already taken", responseCode = "409", content = @Content)})
    @PostMapping("/register")
    public ResponseEntity<OutboundUserRegistrationResponseDto> register(@Valid @RequestBody InboundUserRegistrationDto inboundUserRegistrationDto, HttpSession session) throws UserAlreadyExistsException, UserLockedException, AuthenticationException {
        OutboundUserRegistrationResponseDto response = userRestService.createUser(inboundUserRegistrationDto);
        UUID sessionID = UUID.randomUUID();
        session.setAttribute(sessionIdName, sessionID);
        userRestService.login(inboundUserRegistrationDto.getUsername(), inboundUserRegistrationDto.getPassword(), sessionID);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    @Operation(summary = "Login a user.", tags = {"User"}, responses = {@ApiResponse(description = "OK", responseCode = "200", content = @Content(mediaType = "application/json",
            schema = @Schema(implementation = OutboundUserLoginResponseDto.class))), @ApiResponse(description = "User locked", responseCode = "401", content = @Content), @ApiResponse(description = "User or password is invalid", responseCode = "401", content = @Content)})
    public ResponseEntity<OutboundUserLoginResponseDto> login(@RequestBody @Valid InboundUserLoginDto inboundUserLoginDto, HttpSession session) throws AuthenticationException, UserLockedException, UserNotFoundException {

        UUID sessionID = UUID.randomUUID();
        session.setAttribute(sessionIdName, sessionID);
        userRestService.login(inboundUserLoginDto.getUsername(), inboundUserLoginDto.getPassword(), sessionID);
        User user= userRestService.checkUserExistence(inboundUserLoginDto.getUsername());
        OutboundUserLoginResponseDto response = new OutboundUserLoginResponseDto(user.getUsername(), user.getFirstName(),user.getLastName());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @PostMapping("/{username}/logout")
    @Operation(summary = "Logs out a user.", tags = {"User"}, responses = {@ApiResponse(description = "OK", responseCode = "200"), @ApiResponse(description = "User not found", responseCode = "404", content = @Content), @ApiResponse(description = "Invalid session", responseCode = "401", content = @Content)})
    public ResponseEntity<Object> logout(@PathVariable String username, HttpSession session) throws InvalidSessionException, UserNotFoundException {

        userRestService.logout(username, (UUID) session.getAttribute(sessionIdName));
        session.removeAttribute(sessionIdName);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{username}/password")
    @Operation(
            summary = "Change password of a user.",
            tags = {"User"},
            responses = {
                    @ApiResponse(
                            description = "OK",
                            responseCode = "200"
                    ),
                    @ApiResponse(description = "User not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Invalid session", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Invalid password", responseCode = "401", content = @Content),
            }
    )
    public ResponseEntity<Object> changePassword(@PathVariable String username, @Valid @RequestBody InboundUserChangePasswordDto inboundUserChangePasswordDto, HttpSession session) throws InvalidSessionException, UserNotFoundException, InvalidPasswordException {
        userRestService.changePassword(username, inboundUserChangePasswordDto, (UUID) session.getAttribute(sessionIdName));
        return new ResponseEntity<>(new ResponseMessage("Password was changed"), HttpStatus.OK);
    }

}


























/*

@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping("user")
@Tag(name = "User", description = "Endpoints for managing user")
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class UserController {
    UserRestService userRestService;
    @Operation(
            summary = "Creates a user in the database.",
            tags = {"User"},
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



