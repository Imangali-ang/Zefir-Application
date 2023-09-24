package com.zefir.security.service.impl;

import com.zefir.models.EmailCode;
import com.zefir.models.User;
import com.zefir.models.dto.Email;
import com.zefir.models.dto.ReceiveOpt;
import com.zefir.repository.EmailCodeRepository;
import com.zefir.repository.UserRepository;
import com.zefir.security.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.Optional;

//TODO: validation
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final EmailCodeRepository emailCodeRepository;
    private final UserRepository userRepository;
    private final JavaMailSender javaMailSender;
    private static final String ALLOWED_CHARACTERS = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";


    @Override
    public void sendVerificationCode(Email email) {
        String verificationCode = generateRandomCode();
        EmailCode emailCode = new EmailCode();
        sendEmail(email.getEmail(), "Код подтверждения", "Ваш код подтверждения: " + verificationCode);
        emailCode.setCode(verificationCode);
        emailCode.setSendingTime(LocalDateTime.now());
        emailCode.setEmail(email.getEmail());
        emailCodeRepository.save(emailCode);
    }

    //TODO: add ExceptionHandling Module
    @Override
    public User getUserFromOpt(ReceiveOpt receiveOpt) {
        EmailCode emailCode = emailCodeRepository.findEmailCodeByEmail(receiveOpt.getEmail())
                .orElseThrow(()-> new SecurityException("can't find email by code"));
        if(!emailCode.getCode().equals(receiveOpt.getCode())){
            return null;
        }
        User user = userRepository.findByEmail(emailCode.getEmail())
                .orElseThrow(()-> new SecurityException("can't find user by email"));
        return user;
    }

    protected void sendEmail(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        javaMailSender.send(message);
    }

    protected String generateRandomCode() {
        SecureRandom random = new SecureRandom();
        StringBuilder code = new StringBuilder(6);

        for (int i = 0; i < 6; i++) {
            int randomIndex = random.nextInt(ALLOWED_CHARACTERS.length());
            char randomChar = ALLOWED_CHARACTERS.charAt(randomIndex);
            code.append(randomChar);
        }

        return code.toString();
    }



}
