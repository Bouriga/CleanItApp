package com.ws.common.repository;

import com.ws.common.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface LoginRepository extends JpaRepository<User, Long> {

     List<User> findByEmailAndPassword(String email, String password);

    @Query("select l.id,l.email,l.role,l.username,l.password from User l where email = :email")
     List<User> findByEmail(@Param("email") String email);

    User findOneByUsername(String username);

}
