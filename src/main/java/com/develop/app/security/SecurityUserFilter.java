package com.develop.app.security;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.develop.app.providers.JWTAuthenticationService;
import com.develop.app.providers.JWTUserProvider;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SecurityUserFilter extends OncePerRequestFilter {

    private  final static String atrribute = "user_id";
    private  final static String prefix = "/user";
    private final static String requestName = "Authorization";

    @Autowired
    private JWTUserProvider jwtProvider;

    @Override
     protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
             throws ServletException, IOException {

         String header = request.getHeader(requestName);
         Boolean isPrefix = request.getRequestURI().startsWith(prefix);

         if (isPrefix) {
             validateHeader(header, request, response,atrribute);
         }
         filterChain.doFilter(request, response);
     }
    private void validateHeader(String header, HttpServletRequest request, HttpServletResponse response, String atrribute) {

        Boolean isHeaderNull = header != null;

        if (isHeaderNull) {
            DecodedJWT token = jwtProvider.validateToken(header);
            if (token == null) {
                JWTAuthenticationService.setUnauthorizedResponse(response);
                return;
            }
            JWTAuthenticationService.setUserIdAttribute(request, token,atrribute);
            JWTAuthenticationService.setAuthentication(token);
        }
    }
}