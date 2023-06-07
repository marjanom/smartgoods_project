package com.example.smartgoods_project.rest.controller;


import com.example.smartgoods_project.entity.models.Project;
import com.example.smartgoods_project.exceptions.ProjectAlreadyExistsException;
import com.example.smartgoods_project.exceptions.ProjectNotExistsException;
import com.example.smartgoods_project.exceptions.UserNotFoundException;
import com.example.smartgoods_project.rest.model.*;
import com.example.smartgoods_project.rest.service.ProjectRestService;
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

import java.util.List;

@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping("api/v2/projects")
@Tag(name = "Project", description = "Endpoints for managing projects.")
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class ProjectController {

    ProjectRestService projectRestService;


    @Operation(summary = "Get all projects from database.", tags = {"Project"}, responses = {
            @ApiResponse(description = "Created", responseCode = "200", content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ResponseMessageDto.class))),
            @ApiResponse(description = "Username not found.", responseCode = "404", content = @Content)
    })
    @GetMapping("/{username}")
    public ResponseEntity<List<ProjectProjectionDto>> getAllProjects(@PathVariable(value = "username") String username)
            throws UserNotFoundException {
        List<ProjectProjectionDto> allProjects = projectRestService.getAllProjects(username);
        return new ResponseEntity<>(allProjects, HttpStatus.OK);
    }

    /*@Operation(summary = "Get all requirements from project.", tags = {"Project"}, responses = {
            @ApiResponse(description = "Created", responseCode = "200", content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ResponseMessageDto.class))),
            @ApiResponse(description = "Username not found.", responseCode = "404", content = @Content),
            @ApiResponse(description = "Project not found.", responseCode = "404", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<Object> getAllRequirementsFromProject(@RequestBody InboundCreateProjectRequestDto inboundCreateProjectRequestDto)
            throws UserNotFoundException, ProjectAlreadyExistsException {
        projectRestService.createProject(inboundCreateProjectRequestDto);
        return new ResponseEntity<>(new ResponseMessageDto("Project succesfully saved."), HttpStatus.OK);
    }*/



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
    @PostMapping("")
    public ResponseEntity<Object> create(@RequestBody InboundCreateProjectRequestDto inboundCreateProjectRequestDto)
            throws UserNotFoundException, ProjectAlreadyExistsException {
        projectRestService.createProject(inboundCreateProjectRequestDto);
        return new ResponseEntity<>(new ResponseMessageDto("Project succesfully saved."), HttpStatus.OK);
    }

    @Operation(summary = "Changes project name.", tags = {"Project"}, responses = {
            @ApiResponse(description = "Updated", responseCode = "200", content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ResponseMessageDto.class))),
            @ApiResponse(description = "Username not found.", responseCode = "404", content = @Content),
            @ApiResponse(description = "Project name could not be changed.", responseCode = "500", content = @Content)
    })
    @PutMapping("/{username}")
    public ResponseEntity<ProjectDisplayDto> updateProjectName(@PathVariable String username, @RequestBody InboundUpdateProjectNameDto inboundUpdateProjectNameDto)
            throws Exception, UserNotFoundException {
        ProjectDisplayDto response = projectRestService.updateProjectName(username, inboundUpdateProjectNameDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(summary = "Deletes project from the database.", tags = {"Project"}, responses = {
            @ApiResponse(description = "Project Deleted", responseCode = "204"),
            @ApiResponse(description = "This project doesn't exist", responseCode = "404", content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteProject(@PathVariable(value = "id") String id)
            throws ProjectNotExistsException {
        projectRestService.deleteProject(id);
        return new ResponseEntity<>(new ResponseMessageDto("Project was deleted successfully."), HttpStatus.OK);
    }


}
