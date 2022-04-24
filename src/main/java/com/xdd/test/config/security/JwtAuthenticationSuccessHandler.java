package com.xdd.test.config.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xdd.test.model.ResultConverter;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    public JwtAuthenticationSuccessHandler() {
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
        if (authentication.isAuthenticated()) {
            Claims claims = Jwts.claims();
            Object principal = authentication.getPrincipal();
            if (principal instanceof UserDetails){
                UserDetails userDetails= (UserDetails) principal;
                claims.setSubject(userDetails.getUsername());
            }
            String token = jwtTokenUtil.generateToken(claims);
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(objectMapper.writeValueAsString(ResultConverter.doResult(token,0)));
        }
    }
}
