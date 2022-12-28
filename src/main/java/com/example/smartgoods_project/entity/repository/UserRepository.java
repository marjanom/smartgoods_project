
package com.example.smartgoods_project.entity.repository;


import com.example.smartgoods_project.entity.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    //List<User> findByUuid(@Param("user_UUID") String uuid);
   // User findByUuid(String uuid);

    //boolean existsByUuid(String uuid);

    //boolean existsByUuid(String uuid);
/*    @Query("SELECT u FROM UserData u WHERE u.uuid =:uuid")
    User findCustomerByEmail(String uuid);*/

        //User findByUsername(String username);

        //List<User> findByUuid(String uuid);

/*    User findByUsername(String username);

    boolean existsByUsername(String username);*/

    boolean findByUuid(String uuid);

    boolean existsUserByUuid(String uuid);

}
