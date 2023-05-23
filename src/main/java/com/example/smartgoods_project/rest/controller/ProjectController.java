package com.example.smartgoods_project.rest.controller;


import com.example.smartgoods_project.exceptions.ProjectAlreadyExistsException;
import com.example.smartgoods_project.exceptions.UserNotFoundException;
import com.example.smartgoods_project.rest.model.ResponseMessageDto;
import com.example.smartgoods_project.rest.service.ProjectRestService;
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

@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping("project")
@Tag(name = "Project", description = "Endpoints for managing project in smart goods.")
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class ProjectController {

    ProjectRestService projectRestService;

    /**
     * Save requirment in the database.
     *
     * @return Returns HTTP Created.
     */
    @Operation(summary = "Create project in the database.", tags = {"Project"}, responses = {
            @ApiResponse(description = "Created", responseCode = "201", content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ResponseMessageDto.class))),
            @ApiResponse(description = "Username not found.", responseCode = "404", content = @Content)
    })
    @PostMapping("/save/{username}")
    public ResponseEntity<Object> create(@PathVariable(value = "username") String username, @RequestBody String project)
            throws UserNotFoundException, ProjectAlreadyExistsException {
        projectRestService.createProject(username, project);
        return new ResponseEntity<>(new ResponseMessageDto("Project succesfully saved."), HttpStatus.OK);
    }


}
