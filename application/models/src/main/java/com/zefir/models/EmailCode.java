package com.zefir.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "email_code")
@Getter
@Setter
public class EmailCode {


    @Id
    private UUID id;

    private String email;

    private String code;

    private LocalDateTime sendingTime;

    private boolean used;


}
