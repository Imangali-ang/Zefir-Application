package com.zefir.security.controller;

import com.zefir.models.CustomUserDetails;
import com.zefir.models.User;
import com.zefir.models.dto.Email;
import com.zefir.models.dto.JwtResponse;
import com.zefir.models.dto.ReceiveOpt;
import com.zefir.security.Utils.JwtUtils;
import com.zefir.security.service.AuthService;
import com.zefir.security.service.impl.AuthServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

//TODo : перенести энд пойнты как констату

@RestController
@AllArgsConstructor
@RequestMapping("/api/login/")
public class AuthController {

    private final AuthService authService;

    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;

//    @PostMapping("send-mail")
//    public ResponseEntity<?> sendmail(@RequestBody LoginRequest loginRequest) {
//        Authentication authentication = authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(
//                        loginRequest.getUserNumber(),
//                        loginRequest.getPassword()
//                )
//        );
//
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//        String jwt = jwtUtils.generateToken(loginRequest.getUserNumber());
//        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
//        List<String> roles = userDetails.getAuthorities().stream()
//                .map(GrantedAuthority::getAuthority)
//                .collect(Collectors.toList());
//
//        return ResponseEntity.ok(new JwtResponse(jwt,
//                userDetails.getId(),
//                userDetails.getUsername(),
//                roles));
//    }

    @GetMapping("send-email")
    public void sendMail(@RequestBody Email email){
        authService.sendVerificationCode(email);
    }

    @PostMapping("email/otp")
    public ResponseEntity<?> receiveOptFromEmail(@RequestBody ReceiveOpt opt){
        User user = authService.getUserFromOpt(opt);
        Authentication authentication = new UsernamePasswordAuthenticationToken(opt, null);
        authentication = authenticationManager.authenticate(authentication);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateToken(user.getEmail());

        return ResponseEntity.ok(new JwtResponse(jwt,
                user.getId(),
                user.getUserName(),
                Arrays.asList(user.getUserRole().name())));
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logoutUser(HttpServletRequest request) {
        SecurityContextHolder.getContext().setAuthentication(null);
        return ResponseEntity.ok("Logged out successfully");
    }

}
