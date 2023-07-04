package com.example.smartgoods_project.rest.controller;

import com.example.smartgoods_project.entity.repository.UserRepository;
import com.example.smartgoods_project.entity.service.UserEntityService;
import com.example.smartgoods_project.exceptions.UserAlreadyExistsException;
import com.example.smartgoods_project.rest.mapper.UserMapper;
import com.example.smartgoods_project.rest.model.InboundUserChangePasswordDto;
import com.example.smartgoods_project.rest.model.InboundUserLoginDto;
import com.example.smartgoods_project.rest.model.InboundUserRegistrationDto;
import com.example.smartgoods_project.rest.service.UserRestService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;


@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {
    final String password = "password";
    final String firstname = "firstname";
    final String lastname = "lastname";
    final String username = "username";
    final String correctPassword = "password";
    final String incorrectPassword = "1234";
    private final String sessionFieldName = "X-SESSION-ID";
    private UUID sessionID = null;
    final String CONTENT_TYPE = "Content-Type";
    final String APPLICATION_JSON = "application/json";

    final String X_SESSION_ID = "X-SESSION-ID";

    final String CHANGE_PASSWORD_PATH = "/api/v2/users/{username}/password";
    @Autowired
    UserMapper userMapper;
    @Autowired
    private ObjectMapper mapper;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private UserRepository userRepository;

    @AfterEach
    void tearDown() {
        userRepository.deleteAll();
    }

    @Test
    void successfulRegistration() throws Exception {

        // Arrange
        InboundUserRegistrationDto inboundUserRegistrationDto = InboundUserRegistrationDto.builder()
                .username(username)
                .password(password)
                .firstName(firstname)
                .lastName(lastname)
                .build();

        // Act && Assert
        this.mockMvc.perform(post("/api/v2/users/register").content(mapper.writeValueAsString(inboundUserRegistrationDto))
                        .header("Content-Type", "application/json"))
                .andExpect(content().contentType("application/json"))
                .andExpect(content().json(mapper.writeValueAsString(userMapper.modelToOutboundDto(userRepository.findByUsername(username)))))
                .andExpect(status().isCreated())
                .andDo(print());
    }

    @Test
    void userAlreadyExists() throws Exception {
        // Arrange
        InboundUserRegistrationDto inboundUserRegistrationDto = InboundUserRegistrationDto.builder()
                .username(username)
                .password(password)
                .firstName(firstname)
                .lastName(lastname)
                .build();

        // Act && Assert
        this.mockMvc.perform(post("/api/v2/users/register").content(mapper.writeValueAsString(inboundUserRegistrationDto))
                .header("Content-Type", "application/json"));
        this.mockMvc.perform(post("/api/v2/users/register").content(mapper.writeValueAsString(inboundUserRegistrationDto))
                        .header("Content-Type", "application/json"))
                .andExpect(status().isConflict())
                .andExpect(content().contentType("application/json"))
                .andExpect(content().json("{\"message\":\"The username is already taken!\"}"))
                .andDo(print());
    }

    @Test
    void erroneousInput() throws Exception {
        // Arrange
        InboundUserRegistrationDto inboundUserRegistrationDto = InboundUserRegistrationDto.builder()
                .username(username)
                .lastName(lastname)
                .build();


        // Act && Assert
        this.mockMvc.perform(post("/api/v2/users/register").content(mapper.writeValueAsString(inboundUserRegistrationDto))
                        .header("Content-Type", "application/json"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$").doesNotExist())
                .andDo(print());
    }


    @Test
    void successfulLogin() throws Exception {

        // Arrange
        InboundUserLoginDto inboundUserLoginDto = InboundUserLoginDto.builder()
                .username(username)
                .password(correctPassword)
                .build();

        // Act && Assert
        HttpSession httpSession = this.mockMvc.perform(post("/users/login").content(mapper.writeValueAsString(inboundUserLoginDto))
                        .header("Content-Type", "application/json"))
                .andExpect(MockMvcResultMatchers.status()
                        .isOk())
                .andExpect(jsonPath("$").doesNotExist())
                .andDo(print())
                .andReturn()
                .getRequest()
                .getSession();

        Assertions.assertNotNull(httpSession.getAttribute(sessionFieldName));
    }

    @Test
    void invalidPasswordOnLogin() throws Exception {

        // Arrange
        InboundUserLoginDto inboundUserLoginDto = InboundUserLoginDto.builder()
                .username(username)
                .password(incorrectPassword)
                .build();

        // Act && Assert
        this.mockMvc.perform(MockMvcRequestBuilders.post("/api/v2/users/login")
                        .content(mapper.writeValueAsString(inboundUserLoginDto))
                        .header("Content-Type", "application/json"))
                .andExpect(status().isUnauthorized())
                .andExpect(content().contentType("application/json"))
                .andExpect(content().json("{\"message\":\"Username or password not correct\"}"))
                .andDo(print());
    }

    @Test
    void incompleteInputOnLogin() throws Exception {

        // Arrange
        InboundUserLoginDto inboundUserLoginDto = InboundUserLoginDto.builder()
                .username(username)
                .build();

        // Act && Assert
        this.mockMvc.perform(MockMvcRequestBuilders.post("api/v2/users/login")
                        .content(mapper.writeValueAsString(inboundUserLoginDto))
                        .header("Content-Type", "application/json"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$").doesNotExist())
                .andDo(print());


    }


    @Test
    void changePwd_userIsLoggedIn() throws Exception {
        // Arrange
        InboundUserChangePasswordDto inboundUserChangePasswordDto = InboundUserChangePasswordDto
                .builder()
                .controlNewPassword("newPassword")
                .newPassword("newPassword")
                .oldPassword(password)
                .build();

        HashMap<String, Object> sessionAttributes = new HashMap<String, Object>();
        sessionAttributes.put(X_SESSION_ID, sessionID);
        // Act && Assert
        this.mockMvc.perform(put(CHANGE_PASSWORD_PATH, username)
                        .content(mapper.writeValueAsString(inboundUserChangePasswordDto))
                        .sessionAttrs(sessionAttributes)
                        .header(CONTENT_TYPE, APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void changePwd_userInvalidSession() throws Exception {
        // Arrange
        InboundUserChangePasswordDto inboundUserChangePasswordDto = InboundUserChangePasswordDto
                .builder()
                .controlNewPassword("newPassword")
                .newPassword("newPassword")
                .oldPassword(password)
                .build();

        HashMap<String, Object> sessionAttributes = new HashMap<>();
        sessionAttributes.put(X_SESSION_ID, UUID.randomUUID());
        // Act && Assert
        this.mockMvc.perform(put(CHANGE_PASSWORD_PATH, username)
                        .sessionAttrs(sessionAttributes)
                        .content(mapper.writeValueAsString(inboundUserChangePasswordDto))
                        .header(CONTENT_TYPE, APPLICATION_JSON))
                .andExpect(status().isUnauthorized())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(content().json("{\"message\":\"The session for the user is invalid.\"}"))
                .andDo(print());
    }

    @Test
    void changePwd_invalidPassword() throws Exception {
        // Arrange
        InboundUserChangePasswordDto inboundUserChangePasswordDto = InboundUserChangePasswordDto
                .builder()
                .controlNewPassword("newPassword")
                .newPassword("aNewPassword")
                .oldPassword(password)
                .build();

        HashMap<String, Object> sessionAttributes = new HashMap<>();
        sessionAttributes.put(X_SESSION_ID, sessionID);
        // Act && Assert
        this.mockMvc.perform(put(CHANGE_PASSWORD_PATH, username).sessionAttrs(sessionAttributes)
                        .content(mapper.writeValueAsString(inboundUserChangePasswordDto))
                        .header(CONTENT_TYPE, APPLICATION_JSON))
                .andExpect(status().isUnauthorized())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(content().json("{\"message\":\"Passwords do not match\"}"))
                .andDo(print());
    }

}