package com.example.smartgoods_project.rest.service;


import com.example.smartgoods_project.entity.service.UserEntityService;
import com.example.smartgoods_project.exceptions.UserAlreadyExistsException;
import com.example.smartgoods_project.exceptions.UserNotFoundException;
import com.example.smartgoods_project.rest.model.InboundUserRegistrationDto;
import com.example.smartgoods_project.rest.model.OutboundUserRegistrationDto;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserRestService {

    @NonNull UserEntityService userEntityService;

    public boolean checkUserExistence(String uuid) {
        if (userEntityService.existsByUuid(uuid)) {
            return true;
        } else {
            return false;
        }
    }


    public OutboundUserRegistrationDto createUser(InboundUserRegistrationDto inboundUserRegistrationDto) throws UserAlreadyExistsException {
        return userEntityService.addUser(inboundUserRegistrationDto);
    }
}
