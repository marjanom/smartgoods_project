package com.example.smartgoods_project.rest.controller;

import com.example.smartgoods_project.exceptions.RequirementAlreadyExistsException;
import com.example.smartgoods_project.exceptions.UserNotFoundException;
import com.example.smartgoods_project.rest.model.OutboundUserRegistrationDto;
import com.example.smartgoods_project.rest.model.RequirementUserRequest;
import com.example.smartgoods_project.rest.model.ResponseMessageDto;
import com.example.smartgoods_project.rest.service.RequirmentRestService;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping("requirments")
@Tag(name = "Requirments", description = "Endpoints for managing requirment")
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class RequirmentController {

    RequirmentRestService requirmentRestService;

    /**
     * Save requirment in the database.
     *
     * @return Returns HTTP Created.
     */

    @Operation(summary = "Create requirment in the database.", tags = {"Requirments"}, responses = {
            @ApiResponse(description = "Created", responseCode = "201", content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = OutboundUserRegistrationDto.class))),
            @ApiResponse(description = "Uuid not found.", responseCode = "404", content = @Content),
            @ApiResponse(description = "Requirement already exists.", responseCode = "409", content = @Content),
    })
    @PostMapping("/save")
    public ResponseEntity<Object> save(@PathVariable String uuid,
                                       @Valid @RequestBody String requirment)
            throws UserNotFoundException, RequirementAlreadyExistsException {
        requirmentRestService.saveRequirment(uuid, requirment);
        return new ResponseEntity<>(new ResponseMessageDto("Requirment saved."), HttpStatus.CREATED);
    }


    // TODO what is the best responseMessage
    /**
     * Check requirment according RuppScheme in the database.
     *
     * @return Returns HTTP OK  .
     */
    @Operation(summary = "Create requirment in the database.", tags = {"Requirments"}, responses = {
            @ApiResponse(description = "Created", responseCode = "201", content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = OutboundUserRegistrationDto.class))),
            @ApiResponse(description = "Uuid not found.", responseCode = "404", content = @Content),
            @ApiResponse(description = "Requirement already exists.", responseCode = "409", content = @Content),
    })
    @PostMapping("/check")
    public ResponseEntity<Object> check(@PathVariable String uuid,
                                        @Valid @RequestBody String requirment)
            throws UserNotFoundException, RequirementAlreadyExistsException {
        requirmentRestService.saveRequirment(uuid, requirment);
        return new ResponseEntity<>(new ResponseMessageDto("Check requirment according RuppScheme."), HttpStatus.OK);
    }

    /**
     * Check requirment according RuppScheme in the database.
     *
     * @return Returns HTTP OK.
     */
    @Operation(summary = "Create requirment in the database.", tags = {"Requirments"}, responses = {
            @ApiResponse(description = "Created", responseCode = "201", content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = OutboundUserRegistrationDto.class))),
            @ApiResponse(description = "Uuid not found.", responseCode = "404", content = @Content),
            @ApiResponse(description = "Requirement already exists.", responseCode = "409", content = @Content),
    })
    @PostMapping("/delete/{uuid}")
    public ResponseEntity<Object> delete(@PathVariable String uuid,
                                      @Valid @RequestBody String requirment)
            throws UserNotFoundException, RequirementAlreadyExistsException {
        requirmentRestService.saveRequirment(uuid, requirment);
        return new ResponseEntity<>(new ResponseMessageDto("Requirment saved."), HttpStatus.OK);
    }


    /**
     * Check requirment according RuppScheme in the database.
     *
     * @return Returns HTTP OK.
     */
    @Operation(summary = "Create requirment in the database.", tags = {"Requirments"}, responses = {
            @ApiResponse(description = "Created", responseCode = "201", content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = OutboundUserRegistrationDto.class))),
            @ApiResponse(description = "Uuid not found.", responseCode = "404", content = @Content),
            @ApiResponse(description = "Requirement already exists.", responseCode = "409", content = @Content),
    })
    @PostMapping("/list/all/{uuid}")
    public ResponseEntity<Object> get(@PathVariable String uuid,
                                      @Valid @RequestBody String requirment)
            throws UserNotFoundException, RequirementAlreadyExistsException {
        requirmentRestService.saveRequirment(uuid, requirment);
        return new ResponseEntity<>(new ResponseMessageDto("Requirment saved."), HttpStatus.CREATED);
    }

}