package com.placement.OtpAssignment.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class JwtTokenGeneratorFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {



        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {




            SecretKey key = Keys.hmacShaKeyFor(SecurityConstrants.JWT_KEY.getBytes());



            String jwt = Jwts.builder()
                    .setIssuer("Prem")
                    .setSubject("JWT Token")
                    .claim("username", authentication.getName())
                    .claim("authorities", populateAuthorities(authentication.getAuthorities()))
                    .setIssuedAt(new Date())
                    .setExpiration(new Date(new Date().getTime()+ 30000000)) // millisecond expiration time of around 8 hours
                    .signWith(key).compact();

            response.setHeader(SecurityConstrants.JWT_HEADER, jwt);

        }

        filterChain.doFilter(request, response);
    }

    private String populateAuthorities(Collection<? extends GrantedAuthority> collection) {

        Set<String> authoritiesSet = new HashSet<>();

        for (GrantedAuthority authority : collection) {
            authoritiesSet.add(authority.getAuthority());
        }
        return String.join(",", authoritiesSet);


    }

    private String getRole(Collection<? extends GrantedAuthority> collection) {

        String role="";
        for(GrantedAuthority ga:collection) {
            role= ga.getAuthority();
        }

        return role;
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {

        return !request.getServletPath().equals("/otp/signIn");
    }

}
