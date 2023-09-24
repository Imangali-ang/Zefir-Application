package com.zefir.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter @Setter
public class ReceiveOpt {
    private String code;
    private String email;
}
