package com.example.smartgoods.entity.service;

import com.example.smartgoods.entity.models.User;

import com.example.smartgoods.entity.repository.UserRepository;
import com.example.smartgoods.exceptions.UserAlreadyExistsException;
import com.example.smartgoods.rest.mapper.UserMapper;
import com.example.smartgoods.rest.model.InboundUserRegistrationDto;
import com.example.smartgoods.rest.model.OutboundUserRegistrationDto;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
            throw new UserAlreadyExistsException("The username is already taken!");
        }
        User user = userMapper.inboundToModel(userDto);
        userRepository.save(user);
        return userMapper.modelToOutboundDto(user);
    }
}
