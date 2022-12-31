package com.example.smartgoods_project.rest.service;


import com.example.smartgoods_project.entity.models.Requirement;
import com.example.smartgoods_project.entity.models.User;
import com.example.smartgoods_project.entity.service.RequirementEntityService;
import com.example.smartgoods_project.entity.service.UserEntityService;
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
public class RequirementRestService {

    @NonNull RequirementEntityService requirementEntityService;
    @NonNull UserEntityService userEntityService;
    @NonNull UserRestService userRestService;

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
     * @param uuid
     * @throws UserNotFoundException
     */
    public List<OutboundRequirementUserRequestDto> listRequirements(String uuid) throws UserNotFoundException {
        User user = new User();
        Long userId;
        List<OutboundRequirementUserRequestDto> responseList = new ArrayList<>();
        //TODO This part should be in extra method defined
        if (!userRestService.checkUserExistence(uuid)) {
            throw new UserNotFoundException("This uuid from user is not found!");
        } else if (userRestService.checkUserExistence(uuid)) {
            user = userEntityService.findUserByUuid(uuid);
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

    /**
     * Save requirment in the database
     *
     * @param uuid
     * @param requirement
     * @throws UserNotFoundException
     */
    public void saveRequirement(String uuid, String requirement) throws UserNotFoundException {
        //TODO Refactoren
        User user = new User();
        Long userId;
        boolean isRuppScheme = true;
        //TODO This part should be in extra method defined
        if (!userRestService.checkUserExistence(uuid)) {
            throw new UserNotFoundException("This uuid from user is not found!");
        } else if (userRestService.checkUserExistence(uuid)) {
            user = userEntityService.findUserByUuid(uuid);
            userId = user.getId();
            isRuppScheme = checkIfRuppScheme(requirement);
            Requirement myProvedRequierement = new Requirement(userId, requirement, isRuppScheme);
            requirementEntityService.save(myProvedRequierement);
        }
    }
}

