package com.zefir.repository;

import com.zefir.models.EmailCode;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface EmailCodeRepository extends CrudRepository<EmailCode , UUID> {

    @Query("select u from User u where u.email=:email")
    Optional<EmailCode> findEmailCodeByEmail(@Param("email") String email);
}
