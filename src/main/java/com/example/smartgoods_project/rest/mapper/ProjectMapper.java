package com.example.smartgoods_project.rest.mapper;

import com.example.smartgoods_project.entity.models.Project;
import com.example.smartgoods_project.entity.models.Requirement;
import com.example.smartgoods_project.entity.models.User;
import com.example.smartgoods_project.rest.model.ProjectDisplayDto;
import com.example.smartgoods_project.rest.model.ProjectProjectionDto;
import com.example.smartgoods_project.rest.model.RequirementsProjectionDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
@Component
public class ProjectMapper {

    public ProjectProjectionDto projectToProjectionDto(Project project, List<Requirement> requirements){
        ProjectProjectionDto projectProjectionDto = ProjectProjectionDto.builder()
                .id(project.getId())
                .projectName(project.getProjectName())
                .requirements(requirementToProjectionDto(requirements))
                .build();
        return projectProjectionDto;
    }

    public ProjectDisplayDto projectToDisplayDto(Optional<Project> project, List<Requirement> requirements){
        ProjectDisplayDto projectDisplayDto = ProjectDisplayDto.builder()
                .id(project.get().getId())
                .username(project.get().getUser().getUsername())
                .projectName(project.get().getProjectName())
                .requirements(requirementToProjectionDto(requirements))
                .build();
        return projectDisplayDto;
    }

    public ProjectDisplayDto projectToNewProjectDisplayDto (Project project){

        ProjectDisplayDto projectDisplayDto = ProjectDisplayDto.builder()
                .id(project.getId())
                .username(project.getUser().getUsername())
                .projectName(project.getProjectName())
                .requirements(Collections.emptyList())
                .build();
        return projectDisplayDto;
    }

    public List<RequirementsProjectionDto> requirementToProjectionDto(List<Requirement> requirements) {
        List<RequirementsProjectionDto> allRequirements = new ArrayList<>();

        for (Requirement requirement : requirements) {
            RequirementsProjectionDto requirementsProjectionDto = RequirementsProjectionDto.builder()
                    .id(requirement.getId())
                    .isRuppScheme(String.valueOf(requirement.isRuppScheme()))
                    .requirement(requirement.getRequirement())
                    .hint(requirement.getHint())
                    .mistake(requirement.getMistake())
                    .suggestion(requirement.getSuggestion())
                    .build();
            allRequirements.add(requirementsProjectionDto);
        }
        return allRequirements;
    }
}
