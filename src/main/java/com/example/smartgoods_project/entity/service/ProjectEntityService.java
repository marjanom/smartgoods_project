package com.example.smartgoods_project.entity.service;

import com.example.smartgoods_project.entity.models.Project;
import com.example.smartgoods_project.entity.models.Requirement;
import com.example.smartgoods_project.entity.repository.ProjectRepository;
import com.example.smartgoods_project.entity.repository.RequirementRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class ProjectEntityService {


    ProjectRepository projectRepository;

    public boolean checkProjectExistence(String project) {
        return projectRepository.existsProjectByProjectName(project);
    }


    public <S extends Project> S save(S entity) {
        return projectRepository.save(entity);
    }

}
