package kalitek.solutions.usermanagement.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.Map;

@Service
public class AuthService {
    @Value("${app.jwt.secret:changeme}")
    private String secret;

    @PostConstruct
    void init() {
        if (secret == null || secret.isBlank()) {
            secret = "changeme";
        }
    }

    public String generateToken(Long userId, Long projectId) {
        Instant now = Instant.now();
        return Jwts.builder()
                .setSubject(userId.toString())
                .claim("projectId", projectId)
                .setIssuedAt(Date.from(now))
                .setExpiration(Date.from(now.plusSeconds(3600)))
                .signWith(SignatureAlgorithm.HS256, secret.getBytes())
                .compact();
    }
}
