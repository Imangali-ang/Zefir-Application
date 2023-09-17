package com.zefir.models;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "users")
@Getter @Setter
public class User {

    @Id
    @GeneratedValue
    private UUID id;
    private String email;
    private String name;
    private Boolean online;

    private Boolean blocked;
    private LocalDateTime createdDate;
    private LocalDateTime lastConnection;
    @Enumerated(EnumType.STRING)
    private UserRole userRole;

    public enum UserRole{
        USER,
        ADMIN;
    }

}
