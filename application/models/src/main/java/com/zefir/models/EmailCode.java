package com.zefir.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "email_code")
@Getter
@Setter
public class EmailCode {


    @Id
    @GeneratedValue
    private UUID id;

    private String email;

    private String code;

    private LocalDateTime sendingTime;

    private boolean used;


}
