package com.zefir.security.service;

import com.zefir.models.EmailCode;
import com.zefir.models.User;
import com.zefir.repository.EmailCodeRepository;
import com.zefir.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final EmailCodeRepository emailCodeRepository;
    private final JavaMailSender javaMailSender;
    private static final String ALLOWED_CHARACTERS = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";


    public void sendVerificationCode(String email) {
        String verificationCode = generateRandomCode();
        EmailCode emailCode = new EmailCode();
        sendEmail(email, "Код подтверждения", "Ваш код подтверждения: " + verificationCode);
        emailCode.setCode(verificationCode);
        emailCode.setSendingTime(LocalDateTime.now());
        emailCode.setEmail(email);
        emailCodeRepository.save(emailCode);
    }


    private void sendEmail(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        javaMailSender.send(message);
    }

    private String generateRandomCode() {
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
