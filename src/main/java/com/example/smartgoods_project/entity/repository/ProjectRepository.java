package com.example.smartgoods_project.entity.repository;


import com.example.smartgoods_project.entity.models.Project;
import com.example.smartgoods_project.entity.models.Requirement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

    List<Project> findAllByUserId(@Param("userId") Long userId);

    Project findByProjectName(String projectName);

    boolean existsByProjectName(String projectName);


    @Transactional
    @Modifying
    @Query(value="UPDATE projects SET project_name = :newProjectName WHERE user_id = :userID AND project_name = :oldProjectName", nativeQuery = true)
    void updateProjectName(@Param("userID") Long userId,@Param("oldProjectName") String oldProjectName, @Param("newProjectName") String newProjectName);

    /*@Query(value="DELETE FROM projects WHERE id = :projectId AND user_id = :userId", nativeQuery = true)
    void deleteProject(Long userId, Long projectId);*/
    void deleteById(Long id);

    @Query(value="SELECT * FROM projects WHERE project_name = :projectName AND user_id = :userId", nativeQuery = true)
    Project findProjectFromSpecificUser(Long userId, String projectName);


}
