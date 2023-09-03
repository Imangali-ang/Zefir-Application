package com.zefir.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.zefir.models.User;

@Repository
public interface UserRepository extends JpaRepository<User , Long> {

}