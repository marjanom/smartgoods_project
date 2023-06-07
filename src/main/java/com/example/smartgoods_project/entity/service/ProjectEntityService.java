package com.example.smartgoods_project.entity.service;

import com.example.smartgoods_project.entity.models.Project;
import com.example.smartgoods_project.entity.models.Requirement;
import com.example.smartgoods_project.entity.repository.ProjectRepository;
import com.example.smartgoods_project.entity.repository.RequirementRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Service
@Log4j2
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class ProjectEntityService{


    @Autowired
    ProjectRepository projectRepository;


    public <S extends Project> S save(S entity) {
        return projectRepository.save(entity);
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
}
