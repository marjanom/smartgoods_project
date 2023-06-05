package com.example.smartgoods_project.entity.service;


import com.example.smartgoods_project.entity.models.User;
import com.example.smartgoods_project.entity.repository.UserRepository;
import com.example.smartgoods_project.exceptions.UserAlreadyExistsException;
import com.example.smartgoods_project.exceptions.UserNotFoundException;
import com.example.smartgoods_project.rest.mapper.UserMapper;
import com.example.smartgoods_project.rest.model.InboundUserRegistrationDto;
import com.example.smartgoods_project.rest.model.OutboundUserRegistrationDto;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;

@Service
@Log4j2
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class UserEntityService {
    UserRepository userRepository;

    UserMapper userMapper;

    public boolean checkUserExistence(String username) {
        return userRepository.existsByUsername(username);
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }


    public OutboundUserRegistrationDto addUser(InboundUserRegistrationDto userDto) throws UserAlreadyExistsException {
        if (checkUserExistence(userDto.getUsername())) {
            throw new UserAlreadyExistsException("The username is already taken");
        }
        User user = userMapper.inboundToModel(userDto);
        userRepository.save(user);
        return userMapper.modelToOutboundDto(user);
    }

    public User incrementFailedLoginCount(User user) {
        user.setFailedLoginCounter(user.getFailedLoginCounter() + 1);
        return userRepository.save(user);
    }

    public User resetFailedCounter(User user) {
        user.setFailedLoginCounter(0);
        return userRepository.save(user);
    }

    public User setLockTime(User user) {

        Long lockTime = new Date(new Date().getTime() + (1000 * 60)).getTime();
        user.setLockedUntil(lockTime);
        return userRepository.save(user);
    }

    public User resetLock(User user) {
        user.setLockedUntil(null);
        return userRepository.save(user);
    }

    public User setSessionId(User user, UUID sessionId) {
        user.setSession(sessionId);
        return userRepository.save(user);
    }
    public User removeSessionId(User user) {
        user.setSession(null);
        return userRepository.save(user);
    }

    public User setPassword(User user, String password) {
        user.setPassword(password);
        return userRepository.save(user);
    }
    public OutboundUserRegistrationDto removeUser(User user) throws UserNotFoundException {
        userRepository.delete(user);
        return userMapper.modelToOutboundDto(user);
    }

    public User setSessionValidUntil(User user) {
        user.refreshSession();
        return userRepository.save(user);
    }
}
