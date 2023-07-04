package com.example.smartgoods_project.rest.service;

import com.example.smartgoods_project.entity.models.User;
import com.example.smartgoods_project.entity.service.UserEntityService;
import com.example.smartgoods_project.exceptions.*;
import com.example.smartgoods_project.rest.mapper.UserMapper;
import com.example.smartgoods_project.rest.model.InboundUserChangePasswordDto;
import com.example.smartgoods_project.rest.model.InboundUserRegistrationDto;
import com.example.smartgoods_project.rest.model.OutboundUserRegistrationDto;
import com.example.smartgoods_project.rest.model.OutboundUserRegistrationResponseDto;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import javax.naming.AuthenticationException;
import java.util.Date;
import java.util.UUID;




@SpringBootTest
class UserRestServiceTest {
    static UUID sessionId;
    private final long sessionValidUntil = new Date().getTime() + 1000 * 60 * 60;
    String password = "password";
    String username = "username";
    String firstname = "thomas";
    String lastname = "scheibelhofer";
    @Mock
    UserEntityService userEntityService;
    @InjectMocks
    UserRestService userRestService;


    User user;


    @BeforeAll
    public static void setUpUUID() {
        sessionId = UUID.randomUUID();
    }

    @BeforeEach
    void setUp() {
        // Create the user in the DB
        user = User.builder()
                .username(username)
                .firstName(firstname)
                .lastName(lastname)
                .session(sessionId)
                .sessionValidUntil(sessionValidUntil)
                .build();
        user.setPassword(password);

    }

    @AfterEach
    void tearDown() {

    }

    @Test
    void comparePassword() {
        // Act
        boolean passwordsAreEqual = userRestService.comparePassword(user, password);

        // Assert
        Assertions.assertTrue(passwordsAreEqual);

    }

    @Test
    void sessionValid() {

        // Act
        boolean sessionIsValid;
        try {
            sessionIsValid = userRestService.sessionStillValid(user);
        } catch (InvalidSessionException e) {
            throw new RuntimeException(e);
        }

        // Assert
        Assertions.assertTrue(sessionIsValid);

    }

    @Test
    void logoutUserOnInvalidSession() {
        user.setSessionValidUntil(null);
        // Act && Assert
        Assertions.assertThrows(InvalidSessionException.class, () -> userRestService.logoutUserOnInvalidSession(user));

    }

    @Test
    void login() {

        // Arrange
        boolean userIsLoggedIn;
        Mockito.when(userEntityService.getUserByUsername(username))
                .thenReturn(user);

        // Act

        try {
            userIsLoggedIn = userRestService.login(username, password, sessionId);
        } catch (UserLockedException e) {
            throw new RuntimeException(e);
        } catch (com.example.smartgoods_project.exceptions.AuthenticationException e) {
            throw new RuntimeException(e);
        }

        // Assert session validity has been renewed
        Assertions.assertTrue(userIsLoggedIn);

    }

    @Test
    void loginWithLockedUser() {

        user.setLockedUntil(new Date().getTime() * 2);

        Mockito.when(userEntityService.getUserByUsername(username))
                .thenReturn(user);

        Assertions.assertThrows(UserLockedException.class, () -> userRestService.login(username, password, sessionId));
    }

    @Test
    void logout() {
        // Arrange
        boolean userIsLoggedOut;
        Mockito.when(userEntityService.getUserByUsername(username))
                .thenReturn(user);

        // Act
        try {
            userIsLoggedOut = userRestService.logout(username, sessionId);
        } catch (InvalidSessionException | UserNotFoundException e) {
            throw new RuntimeException(e);
        }

        // Assert
        Assertions.assertTrue(userIsLoggedOut);
    }

    @Test
    void logoutWithInvalidSession() {
        Mockito.when(userEntityService.getUserByUsername(username))
                .thenReturn(user);

        Assertions.assertThrows(InvalidSessionException.class, () -> userRestService.logout(username, UUID.randomUUID()));
    }

    @Test
    void logoutWithInvalidUsername() {
        Mockito.when(userEntityService.getUserByUsername(username))
                .thenReturn(user);

        Assertions.assertThrows(UserNotFoundException.class, () -> userRestService.logout("wrongUsername", sessionId));
    }


    @Test
    void createUserWithExistingUsername() {
        //Arrange
        UserMapper userMapper = new UserMapper();
        InboundUserRegistrationDto inboundUserRegistrationDto = InboundUserRegistrationDto.builder()
                .username(username)
                .password(password)
                .firstName(firstname)
                .lastName(lastname)
                .build();


        try {
            Mockito.when(userEntityService.addUser(inboundUserRegistrationDto)).thenThrow(UserAlreadyExistsException.class);
        } catch (UserAlreadyExistsException e) {
            throw new RuntimeException(e);
        }

        // Act
        Assertions.assertThrows(UserAlreadyExistsException.class, () -> userRestService.createUser(inboundUserRegistrationDto));
    }


    @Test
    void changePassword() {
        // Arrange
        InboundUserChangePasswordDto inboundUserChangePasswordDto = InboundUserChangePasswordDto.builder()
                .oldPassword("password")
                .newPassword("newPassword")
                .controlNewPassword("newPassword")
                .build();

        Mockito.when(userEntityService.getUserByUsername(username))
                .thenReturn(user);


        // Act
        boolean passwordChanged;
        try {
            passwordChanged = userRestService.changePassword(username, inboundUserChangePasswordDto, sessionId);
        } catch (UserNotFoundException | InvalidSessionException | InvalidPasswordException e) {
            throw new RuntimeException(e);
        }

        // Assert
        Assertions.assertTrue(passwordChanged);
    }

    @Test
    void changePasswordWithInvalidPassword() {
        InboundUserChangePasswordDto inboundUserChangePasswordDto = InboundUserChangePasswordDto.builder()
                .oldPassword("password")
                .newPassword("newPassword")
                .controlNewPassword("newerPassword")
                .build();

        Mockito.when(userEntityService.getUserByUsername(username))
                .thenReturn(user);


        Assertions.assertThrows(InvalidPasswordException.class, () -> userRestService.changePassword(username, inboundUserChangePasswordDto, sessionId));
    }


    @Test
    void removeUserByUsername() {
        Mockito.when(userEntityService.getUserByUsername(username))
                .thenReturn(user);

        boolean userRemoved;
        try {
            userRemoved = userRestService.removeUserByUsername(username, password, sessionId);
        } catch (UserNotFoundException | InvalidSessionException | InvalidPasswordException e) {
            throw new RuntimeException(e);
        }

        Assertions.assertTrue(userRemoved);

    }

}