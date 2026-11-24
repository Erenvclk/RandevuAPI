package com.example.randevu.service;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import org.springframework.beans.factory.annotation.Value;

import com.example.randevu.enums.Rol;

@Service
public class JwtService {
    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.sure-dk}")
    private long sureDK;

    private SecretKey anahtar() {
        return Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }

    public String tokenUret(String email, Rol rol) {
        Date simdi = new Date();
        Date bitis = new Date(simdi.getTime() + sureDK * 60_000);

        return Jwts.builder()
                .subject(email)
                .claim("rol", rol.name())
                .issuedAt(simdi)
                .expiration(bitis)
                .signWith(anahtar())
                .compact();
    }

    public Claims tokenOku(String token) {
        return Jwts.parser()
                .verifyWith(anahtar())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}
