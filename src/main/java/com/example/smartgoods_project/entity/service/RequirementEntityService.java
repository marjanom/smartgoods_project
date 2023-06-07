package com.example.smartgoods_project.entity.service;

import com.example.smartgoods_project.entity.models.Requirement;
import com.example.smartgoods_project.entity.repository.RequirementRepository;
import com.example.smartgoods_project.entity.repository.UserRepository;
import com.example.smartgoods_project.exceptions.RequirementNotExistsException;
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


   public void updateProjectName(Long userId, Long oldProjectId, String newProjectName){
        List<Requirement> requirements = requirementRepository.findByUserIdAndProjectName(userId, oldProjectId);

        for (Requirement requirement : requirements) {
            String currentRequirement = requirement.getRequirement();
            String oldProjectName = requirement.getProject().getProjectName();
            System.out.println("Before update: " + currentRequirement);
            currentRequirement = currentRequirement.replace(oldProjectName, newProjectName);
            System.out.println("After update: " + currentRequirement);
            //requirement.setRequirement(currentRequirement);
        }

    }

    public Requirement editRequirement(Long id, String requirement) throws RequirementNotExistsException {
       requirementRepository.updateRequirement(id, requirement);
       Optional<Requirement> updatedRequirement = requirementRepository.findById(id);
       if(updatedRequirement.isPresent()){
           return updatedRequirement.get();
       } else {
           throw new RequirementNotExistsException("This requirement does not exist.");
       }
    }

    public List<Requirement> findAllByUserId(Long userId) {
        return requirementRepository.findAllByUserId(userId);
    }


    public List<Requirement> findByUserIdAndProjectName(Long userId, Long projectId) {
        return requirementRepository.findByUserIdAndProjectName(userId, projectId);
    }

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
