package com.example.smartgoods_project.rest.service;


import com.example.smartgoods_project.entity.models.Project;
import com.example.smartgoods_project.entity.models.Requirement;
import com.example.smartgoods_project.entity.models.User;
import com.example.smartgoods_project.entity.service.ProjectEntityService;
import com.example.smartgoods_project.entity.service.RequirementEntityService;
import com.example.smartgoods_project.entity.service.UserEntityService;
import com.example.smartgoods_project.exceptions.ProjectAlreadyExistsException;
import com.example.smartgoods_project.exceptions.ProjectNotExistsException;
import com.example.smartgoods_project.exceptions.RequirementNotExistsException;
import com.example.smartgoods_project.exceptions.UserNotFoundException;
import com.example.smartgoods_project.rest.model.InboundRequirementRequestDto;
import com.example.smartgoods_project.rest.model.OutboundRequirementUserRequestDto;
import com.example.smartgoods_project.rest.model.OutboundRequirmentResponseDto;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RequirementRestService {

    @NonNull RequirementEntityService requirementEntityService;
    @NonNull UserEntityService userEntityService;
    @NonNull UserRestService userRestService;
    @NonNull ProjectRestService projectRestService;
    @NonNull ProjectEntityService projectEntityService;

    static final Logger log =
            LoggerFactory.getLogger(RequirementRestService.class);

    /**
     * Save requirment in the database
     *
     * @param id
     */
    public boolean checkRequirmentExistance(String id) {
        Long reqId = Long.parseLong(id);
        return requirementEntityService.existsById(reqId);
    }

    /**
     * Save requirment in the database
     *
     * @param id
     * @throws RequirementNotExistsException
     */
    public void removeRequirement(String id) throws RequirementNotExistsException {
        if (!checkRequirmentExistance(id)) {
            throw new RequirementNotExistsException("This requirement could not be found.");
        }
        Long reqId = Long.parseLong(id);
        requirementEntityService.deleteById(reqId);
    }

    /**
     * Save requirment in the database
     *
     * @param username
     * @throws UserNotFoundException
     */
    public List<OutboundRequirementUserRequestDto> listRequirements(String username) throws UserNotFoundException {
        User user = new User();
        Long userId;
        List<OutboundRequirementUserRequestDto> responseList = new ArrayList<>();
        if (!userRestService.checkBoolUserExistence(username)) {
            throw new UserNotFoundException("This username from user is not found!");
        } else if (userRestService.checkBoolUserExistence(username)) {
            user = userEntityService.getUserByUsername(username);
            userId = user.getId();
            List<Requirement> allRequirements = requirementEntityService.findAllByUserId(userId);
            for (Requirement r : allRequirements) {
                String a = r.getRequirement().replace("\"", "");
                OutboundRequirementUserRequestDto outboundRequirementUserRequestDto = new OutboundRequirementUserRequestDto(r.getId(), a, r.isRuppScheme());
                responseList.add(outboundRequirementUserRequestDto);
            }
        }
        return responseList;
    }

    /**
     * (Boolean)Check requirment according Rupp Scheme.
     *
     * @param requirement
     */
    public boolean checkIfRuppScheme(String requirement) {
        String[] requiredWords = new String[]{"shall", "should", "will", "with", "the", "ability", "to", "be", "able", "to"};
        if (requirement.contains(requiredWords[0]) || requirement.contains(requiredWords[1]) || requirement.contains(requiredWords[2])) {
            return true;
        } else return false;
    }

    /**
     * (String)Check requirment according Rupp Scheme.
     *
     * @param requirement
     */
    public String checkIfRuppSchemeToString(String requirement) {
        String isRuppScheme = String.valueOf(checkIfRuppScheme(requirement));
        return isRuppScheme;
    }


    public Requirement saveRequirement(String username, InboundRequirementRequestDto inboundRequirementRequestDto) throws UserNotFoundException, ProjectNotExistsException {
        User user = new User();
        Long userId;
        boolean isRuppScheme = true;
        if (!projectRestService.checkProjectExistance(inboundRequirementRequestDto.getProjectName())) {
            throw new ProjectNotExistsException("This project doesn't exists.");
        } else if (projectRestService.checkProjectExistance(inboundRequirementRequestDto.getProjectName())) {
            if (!userRestService.checkBoolUserExistence(username)) {
                throw new UserNotFoundException("This username from user is not found!");

            } else if (userRestService.checkBoolUserExistence(username)) {
                user = userEntityService.getUserByUsername(username);
                userId = user.getId();
                log.info("hollllaaa");
                isRuppScheme = checkIfRuppScheme(inboundRequirementRequestDto.getRequirement());
                Requirement myProvedRequierement = new Requirement(userId, inboundRequirementRequestDto.getProjectName(), inboundRequirementRequestDto.getRequirement(), isRuppScheme);
                Project existingProject = new Project(userId, inboundRequirementRequestDto.getProjectName(), inboundRequirementRequestDto.getRequirement());
                log.info("hollllaaa222222");
                Requirement requirement = requirementEntityService.save(myProvedRequierement);
                projectEntityService.save(existingProject);
                return requirement;
            }
        }
        return null;
    }


/*
    public void saveRequirement(String username, String requirement) throws UserNotFoundException, ProjectAlreadyExistsException {
        User user;
        Long userId;
        boolean isRuppScheme = true;
        if (!userRestService.checkBoolUserExistence(username)) {
            throw new UserNotFoundException("This username from user is not found!");
        } else if (userRestService.checkBoolUserExistence(username)) {
            user = userEntityService.getUserByUsername(username);
            userId = user.getId();
            //Project project1 = new Project(userId, project);
            isRuppScheme = checkIfRuppScheme(requirement);
            Requirement requirement1 = new Requirement(userId, requirement, isRuppScheme);
            //projectEntityService.save(project1);
            requirementEntityService.save(requirement1);

        }
    }
*/


/*    public void saveRequirement(String username, String project, String requirement) throws UserNotFoundException, ProjectNotExistsException {
        User user = new User();
        Long userId;
        boolean isRuppScheme = true;
        if (!projectRestService.checkProjectExistance(project)) {
            throw new ProjectNotExistsException("This project doesn't exists.");
        } else if (projectRestService.checkProjectExistance(project)) {
            if (!userRestService.checkBoolUserExistence(username)) {
                throw new UserNotFoundException("This username from user is not found!");

            } else if (userRestService.checkBoolUserExistence(username)) {
                user = userEntityService.getUserByUsername(username);
                userId = user.getId();
                log.info("hollllaaa");
                isRuppScheme = checkIfRuppScheme(requirement);
                Requirement myProvedRequierement = new Requirement(userId, project, requirement, isRuppScheme);
                Project existingProject = new Project(userId, project, requirement);
                log.info("hollllaaa222222");
                requirementEntityService.save(myProvedRequierement);
                projectEntityService.save(existingProject);

            }
        }
    }*/
}
