package com.example.smartgoods_project.rest.controller;

import com.example.smartgoods_project.exceptions.ProjectAlreadyExistsException;
import com.example.smartgoods_project.exceptions.ProjectNotExistsException;
import com.example.smartgoods_project.exceptions.RequirementNotExistsException;
import com.example.smartgoods_project.exceptions.UserNotFoundException;
import com.example.smartgoods_project.rest.model.OutboundRequirementUserRequestDto;
import com.example.smartgoods_project.rest.model.OutboundUserRegistrationDto;
import com.example.smartgoods_project.rest.model.ResponseMessageDto;
import com.example.smartgoods_project.rest.service.RequirementRestService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping("requirement")
@Tag(name = "Requirement", description = "Endpoints for managing requirment.")
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class RequirementController {

    RequirementRestService requirementRestService;

    /**
     * Save requirment in the database.
     *
     * @return Returns HTTP Created.
     */
    @Operation(summary = "Create requirement in the database.", tags = {"Requirement"}, responses = {
            @ApiResponse(description = "Created", responseCode = "201", content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ResponseMessageDto.class))),
            @ApiResponse(description = "Username not found.", responseCode = "404", content = @Content)
    })
    @PostMapping("/save/{username}")
    public ResponseEntity<Object> insert (@PathVariable(value = "username") String username, @RequestBody String requirement)
            throws UserNotFoundException, ProjectNotExistsException, ProjectAlreadyExistsException {
        requirementRestService.saveRequirement(username, requirement);
        return new ResponseEntity<>(new ResponseMessageDto("Requirement succesfully saved."), HttpStatus.OK);
    }



    /**
     * List all requirments from the database.
     *
     * @return List of all requirements.
     */
    @Operation(summary = "List all requirements.", tags = {"Requirement"}, responses = {
            @ApiResponse(description = "Created", responseCode = "201", content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ResponseMessageDto.class))),
            @ApiResponse(description = "Username not found.", responseCode = "404", content = @Content),
    })
    @GetMapping("/list/all/{username}")
    public ResponseEntity<List> list(@PathVariable String username)
            throws UserNotFoundException {
        List<OutboundRequirementUserRequestDto> outboundRequirementUserRequestDto = requirementRestService.listRequirements(username);
        return new ResponseEntity<>(outboundRequirementUserRequestDto, HttpStatus.OK);
    }

    /**
     * Delete requirment in the database.
     *
     * @return Returns HTTP Created.
     */
    @Operation(summary = "Delete requirment in the database.", tags = {"Requirement"}, responses = {
            @ApiResponse(description = "Created", responseCode = "201", content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = OutboundUserRegistrationDto.class))),
            @ApiResponse(description = "Requirement not found.", responseCode = "409", content = @Content),
    })
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> delete(@PathVariable String id)
            throws RequirementNotExistsException {
        requirementRestService.removeRequirement(id);
        return new ResponseEntity<>(new ResponseMessageDto("Requirment deleted."), HttpStatus.OK);
    }

    /**
     * Check requirment in the database.
     *
     * @return Returns HTTP Created.
     */
    @Operation(summary = "Check requirment according Rupp Scheme.", tags = {"Requirement"}, responses = {
            @ApiResponse(description = "Checked", responseCode = "201", content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = OutboundUserRegistrationDto.class))),
            @ApiResponse(description = "Requirement not found.", responseCode = "409", content = @Content),
    })
    @PostMapping("/check")
    public ResponseEntity<String> check(@Valid @RequestBody String requirment) {
        return new ResponseEntity<>(requirementRestService.checkIfRuppSchemeToString(requirment), HttpStatus.OK);
    }


}