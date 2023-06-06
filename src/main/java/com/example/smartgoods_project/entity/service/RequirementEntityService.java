package com.example.smartgoods_project.entity.service;

import com.example.smartgoods_project.entity.models.Requirement;
import com.example.smartgoods_project.entity.repository.RequirementRepository;
import com.example.smartgoods_project.entity.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j2;
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
public class RequirementEntityService {

    RequirementRepository requirementRepository;


   /* public void updateProjectName(Long userId, String oldProjectName, String newProjectName){
        List<Requirement> requirements = findByUserIdAndProjectName(userId, oldProjectName);

        for (Requirement requirement : requirements) {
            String currentRequirement = requirement.getRequirement();
            currentRequirement.replace(oldProjectName, newProjectName);
            requirement.setRequirement(currentRequirement);
        }

    }*/

    public List<Requirement> findAllByUserId(Long userId) {
        return requirementRepository.findAllByUserId(userId);
    }

    /*@Override
    public List<Requirement> findByUserIdAndProjectName(Long userId, String projectName) {
        return requirementRepository.findByUserIdAndProjectName(userId, projectName);
    }*/


    public <S extends Requirement> S save(S entity) {
        return requirementRepository.save(entity);
    }

    public void deleteById(Long aLong) {
        requirementRepository.deleteById(aLong);
    }


    public boolean existsById(Long aLong) {
        return requirementRepository.existsById(aLong);
    }


}
