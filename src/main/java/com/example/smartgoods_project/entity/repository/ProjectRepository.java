package com.example.smartgoods_project.entity.repository;


import com.example.smartgoods_project.entity.models.Project;
import com.example.smartgoods_project.entity.models.Requirement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

    List<Project> findAllByUserId(@Param("userId") Long userId);

    Project findByProjectName(String projectName);

    boolean existsByProjectName(String projectName);

    @Query(value="UPDATE projects SET project = :newProjectName WHERE user_id = :userID AND project = :oldProjectName", nativeQuery = true)
    void updateProjectName(@Param("userID") Long userId,@Param("oldProjectName") String oldProjectName, @Param("newProjectName") String newProjectName);
}
