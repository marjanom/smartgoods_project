package com.example.smartgoods_project.helper;

import com.example.smartgoods_project.entity.models.Project;
import com.example.smartgoods_project.entity.models.User;
import com.example.smartgoods_project.entity.service.ProjectEntityService;
import com.example.smartgoods_project.entity.service.UserEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class IdentifierUtils {
    @Autowired
    UserEntityService userEntityService;

    @Autowired
    ProjectEntityService projectEntityService;

    public Long getUserId(String username){
        Long id;
        User user = userEntityService.getUserByUsername(username);
        id = user.getId();
        return id;
    }

    public String getProjectNameFromId(Long id){
        String projectName;
        Optional<Project> project = projectEntityService.findProjectById(id);
        if(project.isPresent()){
            projectName = project.get().getProjectName();
            return projectName;
        }
        return "";
    }
}
