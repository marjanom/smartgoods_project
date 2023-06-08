package com.example.smartgoods_project.rest.service;


import com.example.smartgoods_project.entity.models.Project;
import com.example.smartgoods_project.entity.models.Requirement;
import com.example.smartgoods_project.entity.models.User;
import com.example.smartgoods_project.entity.service.ProjectEntityService;
import com.example.smartgoods_project.entity.service.RequirementEntityService;
import com.example.smartgoods_project.entity.service.UserEntityService;
import com.example.smartgoods_project.exceptions.ProjectNotExistsException;
import com.example.smartgoods_project.exceptions.RequirementNotExistsException;
import com.example.smartgoods_project.exceptions.UserNotFoundException;
import com.example.smartgoods_project.rest.mapper.RequirementMapper;
import com.example.smartgoods_project.rest.model.*;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.CoreDocument;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    RequirementMapper requirementMapper;


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

        String input = requirement.toLowerCase();
        String hint;
        String sentenceStructure = labelPartsOfSpeech(requirement);

        if (input.contains("shall") || input.contains("should") || input.contains("will")) {
            if (input.contains("shall be able to") || input.contains("shall provide") && input.contains("the ability to")
                    || input.contains("should be able to") || input.contains("should provide") && input.contains("the ability to")
                    || input.contains("will be able to") || input.contains("will provide") && input.contains("the ability to")) {
                if(sentenceStructure.contains("VBJJTOVB") || sentenceStructure.contains("DTNNTOVB")){
                    return true;
                } else {
                    hint = "Process verb after key phrase (be able to/ provide <someone> the abiility to) is missing!";
                    System.out.println(hint);
                }
            } else {
                hint = "Key phrase (be able to, provide <someone> the ability to) is missing!";
                System.out.println(hint);
            }
        } else {
            hint = "Keyword (shall, should, will) is missing!";
            System.out.println(hint);
        }
        return false;
    }

    public OutboundRuppSchemeResponseDto checkIfRuppSchemeNoDB(String requirement) throws Exception {

        String input = requirement.toLowerCase();
        String hint;
        String sentenceStructure = labelPartsOfSpeech(requirement);
        OutboundRuppSchemeResponseDto outboundRuppSchemeResponseDto = new OutboundRuppSchemeResponseDto();

        try {
            if (input.contains("shall") || input.contains("should") || input.contains("will")) {
                if (input.contains("shall be able to") || input.contains("shall provide") && input.contains("the ability to")
                        || input.contains("should be able to") || input.contains("should provide") && input.contains("the ability to")
                        || input.contains("will be able to") || input.contains("will provide") && input.contains("the ability to")) {
                    if (sentenceStructure.contains("VBJJTOVB") || sentenceStructure.contains("DTNNTOVB")) {
                        outboundRuppSchemeResponseDto.setRuppScheme(true);
                        outboundRuppSchemeResponseDto.setHint("");
                        return outboundRuppSchemeResponseDto;
                    } else {
                        hint = "Process verb after key phrase (be able to/ provide <someone> the abiility to) is missing!";
                        outboundRuppSchemeResponseDto.setHint(hint);
                    }
                } else {
                    hint = "Key phrase (be able to, provide <someone> the ability to) is missing!";
                    outboundRuppSchemeResponseDto.setHint(hint);
                }
            } else {
                hint = "Keyword (shall, should, will) is missing!";
                outboundRuppSchemeResponseDto.setHint(hint);
            }
            outboundRuppSchemeResponseDto.setRuppScheme(false);
            return outboundRuppSchemeResponseDto;
        }catch (Exception ex){
            throw new Exception("Error while processing this request");
        }
    }

    /**
     * (String)Check requirment according Rupp Scheme.
     *
     * @param requirement
     */

    public OutboundRequirmentResponseDto saveRequirement(InboundRequirementRequestDto inboundRequirementRequestDto) throws UserNotFoundException, ProjectNotExistsException {
        User user = new User();
        RequirementMapper mapper = new RequirementMapper();
        String username = inboundRequirementRequestDto.getUsername();
        Long userId;
        boolean isRuppScheme = true;
        if (!projectRestService.checkProjectExistance(inboundRequirementRequestDto.getProjectName())) {
            throw new ProjectNotExistsException("This project does not exists.");
        } else if (projectRestService.checkProjectExistance(inboundRequirementRequestDto.getProjectName())) {
            if (!userRestService.checkBoolUserExistence(username)) {
                throw new UserNotFoundException("This username does not exist");

            } else if (userRestService.checkBoolUserExistence(username)) {
                user = userEntityService.getUserByUsername(username);
                userId = user.getId();
                isRuppScheme = checkIfRuppScheme(inboundRequirementRequestDto.getRequirement());
                Project project = projectEntityService.findProject(inboundRequirementRequestDto.getProjectName());
                Requirement myProvedRequierement = new Requirement(userId,project, inboundRequirementRequestDto.getRequirement(), isRuppScheme);
                Requirement requirement = requirementEntityService.save(myProvedRequierement);
                OutboundRequirmentResponseDto outboundRequirmentResponseDto = mapper.DbResponseToResponseDto(requirement, username);
                return outboundRequirmentResponseDto;
            }
        }
        return null;
    }

    public OutboundEditRequirementDto editRequirement(String id, InboundUpdateRequirementDto inboundUpdateRequirementDto) throws RequirementNotExistsException {
        Long requirementId = Long.valueOf(id);
        Requirement updatedRequirement = requirementEntityService.editRequirement(requirementId, inboundUpdateRequirementDto.getRequirement());
        OutboundEditRequirementDto outboundEditRequirementDto = requirementMapper.DbResponseToDisplay(updatedRequirement);
        return outboundEditRequirementDto;
    }



    private String labelPartsOfSpeech(String input){
        StringBuilder sentenceStructure = new StringBuilder();

        StanfordCoreNLP stanfordCoreNLP = Pipeline.getInstance();
        CoreDocument coreDocument = new CoreDocument(input);
        stanfordCoreNLP.annotate(coreDocument);
        List<CoreLabel> coreLabels = coreDocument.tokens();

        for(CoreLabel coreLabel : coreLabels){
            String pos = coreLabel.get(CoreAnnotations.PartOfSpeechAnnotation.class);
            sentenceStructure.append(pos);
            //System.out.println(sentenceStructure);
            //System.out.println(coreLabel.originalText() + "=" + pos);
        }
        return sentenceStructure.toString();
    }
}
