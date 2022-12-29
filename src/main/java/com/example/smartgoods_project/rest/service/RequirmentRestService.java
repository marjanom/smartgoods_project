package com.example.smartgoods_project.rest.service;


import com.example.smartgoods_project.entity.service.RequirmentEntityService;
import com.example.smartgoods_project.entity.service.UserEntityService;
import com.example.smartgoods_project.exceptions.RequirementAlreadyExistsException;
import com.example.smartgoods_project.exceptions.UserNotFoundException;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RequirmentRestService {



    @NonNull RequirmentEntityService requirmentEntityService;
    @NonNull UserEntityService userEntityService;
    UserRestService userRestService;


    public void removeRequirment (){}

    public boolean checkRequirment (){
        return false;
    }


    public void showRequirment (){}


    public boolean checkRequirmentExistence(String requirment) throws RequirementAlreadyExistsException {

    }


    public boolean checkIfRuppScheme(String requirment) throws RequirementAlreadyExistsException {

        return false;
    }



    /**
     * Save requirment in the database
     *
     * @param uuid
     * @param requirment
     * @throws UserNotFoundException
     */
    public void saveRequirment(String uuid, String requirment) throws UserNotFoundException, RequirementAlreadyExistsException {
        userRestService.checkUserExistence(uuid);
        checkRequirmentExistence(requirment);
        // TODO not finished
        if (!checkRequirmentExistence(requirment));
            requirmentEntityService.save(requirment);


    }
}
