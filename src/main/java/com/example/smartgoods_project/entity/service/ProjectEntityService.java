package com.example.smartgoods_project.entity.service;

import com.example.smartgoods_project.entity.models.Project;
import com.example.smartgoods_project.entity.models.Requirement;
import com.example.smartgoods_project.entity.models.User;
import com.example.smartgoods_project.entity.repository.ProjectRepository;
import com.example.smartgoods_project.entity.repository.RequirementRepository;
import com.example.smartgoods_project.exceptions.UserNotFoundException;
import com.example.smartgoods_project.helper.IdentifierUtils;
import com.example.smartgoods_project.rest.mapper.ProjectMapper;
import com.example.smartgoods_project.rest.model.ProjectProjectionDto;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Log4j2
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class ProjectEntityService{


    @Autowired
    ProjectRepository projectRepository;

    @Autowired
    ProjectMapper projectMapper;

    @Autowired
    UserEntityService userEntityService;

    @Autowired
    RequirementEntityService requirementEntityService;



    public <S extends Project> S save(S entity) {
        return projectRepository.save(entity);
    }

    public List<ProjectProjectionDto> findAllProjects(Long userId){
        List<ProjectProjectionDto> allProjects = new ArrayList<>();
        List<Project> allProjectsFromDB = projectRepository.findAllProjects(userId);
        List<Requirement> requirements = new ArrayList<>();

        for(Project project : allProjectsFromDB){
            requirements = requirementEntityService.findByUserIdAndProjectName(userId, project.getId());
            ProjectProjectionDto projectProjectionDto = projectMapper.projectToProjectionDto(project, requirements);
            allProjects.add(projectProjectionDto);
        }

        return allProjects;
    }

    public boolean existsByProject(String projectName) {
        return projectRepository.existsByProjectName(projectName);
    }


    public void updateProjectName(Long userId, String oldProjectName, String newProjectName) {
        projectRepository.updateProjectName(userId, oldProjectName, newProjectName);
    }

    public Project findProject(String projectName){
        return projectRepository.findByProjectName(projectName);
    }

    public Optional<Project> findProjectById(Long id){
        return projectRepository.findById(id);
    }

    public Project findProjectFromSpecificUser(Long userId, String projectName){
        return projectRepository.findProjectFromSpecificUser(userId, projectName);
    }

    public void deleteProject(Long projectId){
        projectRepository.deleteById(projectId);
    }

    public boolean checkIfUserHasAlreadyProject(String username, String projectName) throws UserNotFoundException {
        User user = userEntityService.getUserByUsername(username);
        Long userId = user.getId();
        Optional<Project> project = projectRepository.checkIfUserHasAlreadyProject(userId, projectName);
        if(project.isEmpty()){
            return false;
        }
        return true;
    }
}
