package com.example.smartgoods_project.rest.service;


import com.example.smartgoods_project.entity.service.UserEntityService;
import com.example.smartgoods_project.exceptions.UserAlreadyExistsException;
import com.example.smartgoods_project.rest.model.InboundUserRegistrationDto;
import com.example.smartgoods_project.rest.model.OutboundUserRegistrationDto;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserRestService {

    @NonNull UserEntityService userEntityService;

/*    private User checkUserExistence(String uuid) throws UserNotFoundException {
        User user = userEntityService.getUserByUuid(uuid);
        if (user == null) {
            throw new UserNotFoundException("The user name does not exist!");
        }
        return user;
    }*/

    public OutboundUserRegistrationDto createUser(InboundUserRegistrationDto inboundUserRegistrationDto) throws UserAlreadyExistsException {
        return userEntityService.addUser(inboundUserRegistrationDto);
    }
}
