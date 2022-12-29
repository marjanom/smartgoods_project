
package com.example.smartgoods_project.entity.repository;


import com.example.smartgoods_project.entity.models.Requirment;
import com.example.smartgoods_project.entity.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequirmentRepository extends JpaRepository<Requirment, Long> {



}
