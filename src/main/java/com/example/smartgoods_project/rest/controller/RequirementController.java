package com.example.smartgoods_project.rest.controller;

import com.example.smartgoods_project.entity.models.Requirement;
import com.example.smartgoods_project.exceptions.ProjectAlreadyExistsException;
import com.example.smartgoods_project.exceptions.ProjectNotExistsException;
import com.example.smartgoods_project.exceptions.RequirementNotExistsException;
import com.example.smartgoods_project.exceptions.UserNotFoundException;
import com.example.smartgoods_project.rest.model.*;
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
@RequestMapping("api/v2/requirements")
@Tag(name = "Requirement", description = "Endpoints for managing requirments.")
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
                    schema = @Schema(implementation = OutboundRequirmentResponseDto.class))),
            @ApiResponse(description = "Username not found.", responseCode = "404", content = @Content)
    })
    @PostMapping("")
    public ResponseEntity<OutboundRequirmentResponseDto> insert(@RequestBody InboundRequirementRequestDto inboundRequirementRequestDto)
            throws UserNotFoundException, ProjectNotExistsException {
        OutboundRequirmentResponseDto response = requirementRestService.saveRequirement(inboundRequirementRequestDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(summary = "Edit a requirement.", tags = {"Requirement"}, responses = {
            @ApiResponse(description = "Edited", responseCode = "200", content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = OutboundEditRequirementDto.class))),
            @ApiResponse(description = "This requirement does not exist.", responseCode = "404", content = @Content),
    })
    @PutMapping("/{id}")
    public ResponseEntity<OutboundEditRequirementDto> editRequirement(@PathVariable(value = "id") String requirementId,@RequestBody InboundUpdateRequirementDto inboundUpdateRequirementDto)
            throws RequirementNotExistsException {
        OutboundEditRequirementDto response = requirementRestService.editRequirement(requirementId, inboundUpdateRequirementDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    /**
     * Delete requirment in the database.
     *
     * @return Returns HTTP OK.
     */
    @Operation(summary = "Delete requirement in the database.", tags = {"Requirement"}, responses = {
            @ApiResponse(description = "Deleted", responseCode = "200"),
            @ApiResponse(description = "Requirement not found.", responseCode = "404", content = @Content),
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable String id) throws RequirementNotExistsException {
        requirementRestService.removeRequirement(id);
        return new ResponseEntity<>(new ResponseMessageDto("Requirement was deleted successfully."), HttpStatus.OK);
    }

    /**
     * Check requirment in the database.
     *
     * @return Returns HTTP Created.
     */
    @Operation(summary = "Check requirement according Rupp Scheme.", tags = {"Requirement"}, responses = {
            @ApiResponse(description = "OK", responseCode = "200", content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = OutboundRuppSchemeResponseDto.class))),
            @ApiResponse(description = "Error while processing this request.", responseCode = "500", content = @Content),
    })
    @PostMapping("/check")
    public ResponseEntity<RequirementAttribute> check(@Valid @RequestBody String requirement) throws Exception {
        RequirementAttribute attributes = requirementRestService.checkIfRuppScheme(requirement);
        return new ResponseEntity<>(attributes, HttpStatus.OK);
    }


}