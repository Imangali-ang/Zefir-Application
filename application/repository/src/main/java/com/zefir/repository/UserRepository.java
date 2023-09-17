package com.zefir.repository;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.zefir.models.User;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User , Long> {

    @Query("select u from User u where u.email=:email")
    Optional<User> findByEmail(@Param("email") String email);

    @Query("select u from User u where u.id = :id")
    Optional<User> findById(@Param("id")UUID id);
}
