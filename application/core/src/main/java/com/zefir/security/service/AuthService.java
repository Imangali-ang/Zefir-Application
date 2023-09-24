package com.zefir.security.service;

import com.zefir.models.User;
import com.zefir.models.dto.Email;
import com.zefir.models.dto.ReceiveOpt;

public interface AuthService {

    void sendVerificationCode(Email email);

    User getUserFromOpt(ReceiveOpt receiveOpt);
}
