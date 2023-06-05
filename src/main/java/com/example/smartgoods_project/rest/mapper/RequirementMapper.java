package com.example.smartgoods_project.rest.mapper;

import com.example.smartgoods_project.entity.models.Requirement;
import com.example.smartgoods_project.entity.models.User;
import com.example.smartgoods_project.rest.model.InboundUserRegistrationDto;
import com.example.smartgoods_project.rest.model.OutboundRequirmentResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public class RequirementMapper {

    public OutboundRequirmentResponseDto DbResponseToResponseDto(Requirement requirement, String username) {

        OutboundRequirmentResponseDto outboundRequirmentResponseDto = OutboundRequirmentResponseDto.builder()
                .id(requirement.getId())
                .username(username)
                .projectName(requirement.getProjectName())
                .requirement(requirement.getRequirement())
                .isRuppScheme(requirement.isRuppScheme())
                .build();
        return outboundRequirmentResponseDto;
    }
}