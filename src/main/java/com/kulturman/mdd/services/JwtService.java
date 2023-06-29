package com.kulturman.mdd.services;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtService {
    @Value("${app.jwtSecret}")
    private String jwtSecret;

    public String generateToken(Authentication authentication) {
        return generateToken(authentication.getName());
    }

    public String generateToken(String email) {
        return Jwts.builder()
            .setSubject(email)
            .setIssuedAt(new Date())
            //24h
            .setExpiration(new Date(System.currentTimeMillis() + 24 * 3600 * 100))
            .signWith(SignatureAlgorithm.HS512, jwtSecret)
            .compact();
    }


    public Jws<Claims> decodeToken(String token) {
        try {
            return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
        }

        catch (SignatureException | ExpiredJwtException | UnsupportedJwtException | MalformedJwtException exception) {
            return null;
        }
    }
}
