package com.example.demo.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.security.Key;
import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class JwtTokenProvider {
    
    @Value("${jwt.secret:super-secret-key}")
    private String jwtSecret;
    
    @Value("${jwt.expiration:86400000}")
    private Long jwtExpirationMs;
    
    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(jwtSecret.getBytes());
    }
    
    public String generateToken(Long userId, String email, Set<String> roles) {
        Date expiryDate = new Date(System.currentTimeMillis() + jwtExpirationMs);
        
        return Jwts.builder()
                .setSubject(email)
                .claim("userId", userId)
                .claim("email", email)
                .claim("roles", String.join(",", roles))
                .setIssuedAt(new Date())
                .setExpiration(expiryDate)
                .signWith(getSigningKey(), SignatureAlgorithm.HS512)
                .compact();
    }
    
    public String getUsername(String token) {
        Claims claims = getClaims(token);
        return claims.getSubject();
    }
    
    public Set<String> getRole(String token) {
        Claims claims = getClaims(token);
        String roles = (String) claims.get("roles");
        return Set.of(roles.split(","));
    }
    
    public Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
    
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(getSigningKey()).build().parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }
}