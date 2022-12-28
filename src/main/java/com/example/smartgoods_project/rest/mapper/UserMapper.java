package com.example.smartgoods.rest.mapper;


import com.example.smartgoods.entity.models.User;
import com.example.smartgoods.rest.model.InboundUserRegistrationDto;
import com.example.smartgoods.rest.model.OutboundUserRegistrationDto;
import org.mapstruct.MappingConstants;
import org.mapstruct.Mapper;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public class UserMapper {

    public User inboundToModel(InboundUserRegistrationDto inboundUserRegistrationDto) {

        User user = User.builder()
                .username(inboundUserRegistrationDto.getUsername())
                .firstName(inboundUserRegistrationDto.getFirstName())
                .lastName(inboundUserRegistrationDto.getLastName())
                .build();
        user.setPassword(inboundUserRegistrationDto.getPassword());
        return user;

    }

    public OutboundUserRegistrationDto modelToOutboundDto(User user) {

        return OutboundUserRegistrationDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .build();
    }

}






/*
@Component
public class UserMapper {


    public InboundUserRegistrationDto convertEntityToDto(User user) {
        ModelMapper modelMapper = new ModelMapper();
        InboundUserRegistrationDto inboundUserRegistrationDto = modelMapper.map(user, InboundUserRegistrationDto.class);
        return inboundUserRegistrationDto;

    }

    public User convertDtoToEntity(InboundUserRegistrationDto inboundUserRegistrationDto) {
        ModelMapper modelMapper = new ModelMapper();
        User user = modelMapper.map(inboundUserRegistrationDto, User.class);
        return user;
    }
*/
