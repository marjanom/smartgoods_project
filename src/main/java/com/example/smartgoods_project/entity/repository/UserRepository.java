
package com.example.smartgoods_project.entity.repository;


import com.example.smartgoods_project.entity.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByUuid(String uuid);

    boolean existsUserByUuid(String uuid);

    User findUserByUuid(String uuid);

}
