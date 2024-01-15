package com.develop.app.providers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import com.auth0.jwt.interfaces.DecodedJWT;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JWTAuthenticationService {
        public static void setAuthentication(DecodedJWT token) {
        List<Object> roles = token.getClaim("roles").asList(Object.class);

        List<SimpleGrantedAuthority> grants = roles.stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.toString().toUpperCase()))
                .collect(Collectors.toList());

        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(token.getSubject(), null,
                grants);
        SecurityContextHolder.getContext().setAuthentication(auth);
    }

    public static void setUnauthorizedResponse(HttpServletResponse response) {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    }

    public static void setUserIdAttribute(HttpServletRequest request, DecodedJWT token, String attribute) {
        request.setAttribute(attribute, token.getSubject());
    }

}
