package com.example.smartgoods_project.rest.service;


import com.example.smartgoods_project.entity.models.Project;
import com.example.smartgoods_project.entity.models.Requirement;
import com.example.smartgoods_project.entity.models.User;
import com.example.smartgoods_project.entity.service.ProjectEntityService;
import com.example.smartgoods_project.entity.service.RequirementEntityService;
import com.example.smartgoods_project.entity.service.UserEntityService;
import com.example.smartgoods_project.exceptions.ProjectAlreadyExistsException;
import com.example.smartgoods_project.exceptions.RequirementNotExistsException;
import com.example.smartgoods_project.exceptions.UserNotFoundException;
import com.example.smartgoods_project.rest.model.OutboundRequirementUserRequestDto;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectRestService {

    @NonNull RequirementEntityService requirementEntityService;
    @NonNull UserEntityService userEntityService;
    @NonNull UserRestService userRestService;
    @NonNull ProjectEntityService projectEntityService;

    static final Logger log =
            LoggerFactory.getLogger(RequirementRestService.class);

    /**
     * Save requirment in the database
     *
     * @param project
     */
    public boolean checkProjectExistance(String project) {
        return projectEntityService.existsByProject(project);
    }

    public void createProject(String username, String project) throws UserNotFoundException, ProjectAlreadyExistsException {
        User user;
        Long userId;
        if (!userRestService.checkBoolUserExistence(username)) {
            throw new UserNotFoundException("This username from user is not found!");
        } else if (userRestService.checkBoolUserExistence(username)) {
            user = userEntityService.getUserByUsername(username);
            userId = user.getId();
            if (checkProjectExistance(project)) {
                throw new ProjectAlreadyExistsException("This user have already this existing project");
            } else {
                Project project1 = new Project(userId, project);
                projectEntityService.save(project1);
            }
        }
    }

}
