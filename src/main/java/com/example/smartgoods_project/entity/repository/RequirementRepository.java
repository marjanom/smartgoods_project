
package com.example.smartgoods_project.entity.repository;


import com.example.smartgoods_project.entity.models.Project;
import com.example.smartgoods_project.entity.models.Requirement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RequirementRepository extends JpaRepository<Requirement, Long> {

    List<Requirement> findAllByUserId(@Param("userId") Long userId);

    Requirement findByUserId(String userId);

    @Query(value="SELECT * FROM requirements WHERE project_id = :projectId AND user_id = :userId", nativeQuery = true)
    List<Requirement> findByUserIdAndProjectName(Long userId, Long projectId);




}
