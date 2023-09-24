package com.zefir.models;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "users")
@Getter @Setter
public class User {

    @Id
    private UUID id;
    private String email;

    private String userName;
    private Boolean online;

    private Boolean blocked;
    private LocalDateTime created;
    private LocalDateTime lastConnection;
    @Enumerated(EnumType.STRING)
    private UserRole userRole;

    public enum UserRole{
        USER,
        ADMIN;
    }

}
