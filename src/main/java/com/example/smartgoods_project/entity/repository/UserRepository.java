
package com.example.smartgoods.entity.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.smartgoods.entity.models.User;

import java.util.List;
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

    User findByUsername(String username);

    boolean existsByUsername(String username);


}
