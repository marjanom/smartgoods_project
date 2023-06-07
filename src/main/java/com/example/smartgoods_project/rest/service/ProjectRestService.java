package com.example.smartgoods_project.rest.service;


import com.example.smartgoods_project.entity.models.Project;
import com.example.smartgoods_project.entity.models.Requirement;
import com.example.smartgoods_project.entity.models.User;
import com.example.smartgoods_project.entity.service.ProjectEntityService;
import com.example.smartgoods_project.entity.service.RequirementEntityService;
import com.example.smartgoods_project.entity.service.UserEntityService;
import com.example.smartgoods_project.exceptions.ProjectAlreadyExistsException;
import com.example.smartgoods_project.exceptions.ProjectNotExistsException;
import com.example.smartgoods_project.exceptions.UserNotFoundException;
import com.example.smartgoods_project.helper.IdentifierUtils;
import com.example.smartgoods_project.rest.mapper.ProjectMapper;
import com.example.smartgoods_project.rest.model.InboundCreateProjectRequestDto;
import com.example.smartgoods_project.rest.model.InboundUpdateProjectNameDto;
import com.example.smartgoods_project.rest.model.ProjectDisplayDto;
import com.example.smartgoods_project.rest.model.ProjectProjectionDto;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProjectRestService {

    static final Logger log =
            LoggerFactory.getLogger(RequirementRestService.class);
    @NonNull RequirementEntityService requirementEntityService;
    @NonNull UserEntityService userEntityService;
    @NonNull UserRestService userRestService;
    @NonNull ProjectEntityService projectEntityService;
    @NonNull ProjectMapper projectMapper;
    @NonNull IdentifierUtils identifierUtils;

    public List<ProjectProjectionDto> getAllProjects(String username) throws UserNotFoundException{
        Long userId = identifierUtils.getUserId(username);
        List<ProjectProjectionDto> allProjects = projectEntityService.findAllProjects(userId);
        return allProjects;
    }


    public void createProject(InboundCreateProjectRequestDto inboundCreateProjectRequestDto) throws UserNotFoundException, ProjectAlreadyExistsException {
        String username = inboundCreateProjectRequestDto.getUsername();
        String projectName = inboundCreateProjectRequestDto.getProjectName();
        User user;

        if (!userRestService.checkBoolUserExistence(username)) {
            throw new UserNotFoundException("This username from user is not found!");
        } else if (userRestService.checkBoolUserExistence(username)) {
            user = userEntityService.getUserByUsername(username);
            if (checkIfUserHasAlreadyProject(username, projectName)) {
                throw new ProjectAlreadyExistsException("This user have already this existing project");
            } else {
                Project newProject = new Project(user, projectName);
                projectEntityService.save(newProject);
            }
        }
    }

    public ProjectDisplayDto updateProjectName(String username, InboundUpdateProjectNameDto inboundUpdateProjectNameDto) throws Exception, UserNotFoundException {
        Long userId;
        Long projectId;

        if (!userRestService.checkBoolUserExistence(username)) {
            throw new UserNotFoundException("This username from user is not found!");
        } else {
            try {
                userId = identifierUtils.getUserId(username);
                projectId = identifierUtils.getProjectIdFromName(userId, inboundUpdateProjectNameDto.getOldProjectName());
                projectEntityService.updateProjectName(userId, inboundUpdateProjectNameDto.getOldProjectName(), inboundUpdateProjectNameDto.getNewProjectName());
                requirementEntityService.updateProjectName(userId, projectId, inboundUpdateProjectNameDto.getNewProjectName());
                List<Requirement> requirements = requirementEntityService.findByUserIdAndProjectName(userId, projectId);
                Optional<Project> renamedProject = projectEntityService.findProjectById(projectId);
                ProjectDisplayDto project = projectMapper.projectToDisplayDto(renamedProject, requirements);
                return project;
            } catch(Exception ex){
                throw new Exception("Project name could not be changed");
            }
            }
    }

    public void deleteProject(String id) throws ProjectNotExistsException{
        long projectId = Long.valueOf(id);
        String projectName = identifierUtils.getProjectNameFromId(projectId);
        if (!checkProjectExistance(projectName) || projectName.equals("")) {
            throw new ProjectNotExistsException("This project doesn't exists.");
        }
        projectEntityService.deleteProject(projectId);
    }

    public boolean checkProjectExistance(String projectName) {
        return projectEntityService.existsByProject(projectName);
    }

    private boolean checkIfUserHasAlreadyProject(String username, String projectName) throws UserNotFoundException {
        return projectEntityService.checkIfUserHasAlreadyProject(username, projectName);

    }

}
