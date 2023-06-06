
package com.example.smartgoods_project.entity.repository;


import com.example.smartgoods_project.entity.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);

    boolean existsByUsername(String username);

    @Query(value="DELETE FROM public.users WHERE id = 1;", nativeQuery = true)
    boolean maker();
}
