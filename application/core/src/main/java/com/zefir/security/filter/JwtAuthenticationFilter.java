package com.zefir.security.filter;

import com.zefir.security.Utils.JwtUtils;
import com.zefir.security.service.CustomUserDetailsService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtils jwtUtils;
    private final CustomUserDetailsService userDetailsService;

    public JwtAuthenticationFilter(JwtUtils jwtUtils, CustomUserDetailsService userDetailsService) {
        this.jwtUtils = jwtUtils;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String headerAuth = request.getHeader("Authorization");
            String jwt=null;
            if (headerAuth != null && headerAuth.startsWith("Bearer ")) {
                jwt = headerAuth.substring(7);
            }

            if (jwt != null && jwtUtils.validateToken(jwt)) {
                String username = jwtUtils.getUsernameFromToken(jwt);
                UserDetails userDetails = userDetailsService.loadUserByEmail(username);
                Authentication authentication = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } catch (Exception e) {
            // Log exception or handle it
        }

        filterChain.doFilter(request, response);
    }
}


