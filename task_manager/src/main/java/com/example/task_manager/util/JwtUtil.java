package com.example.task_manager.util;

import com.example.task_manager.entity.Users;
import com.example.task_manager.repository.UserRepo;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.*;
import java.util.function.Function;

@Component
public class JwtUtil {

    @Autowired
    private UserRepo userRepo;

    private final SecretKey accessSecreteKey = Keys.hmacShaKeyFor("/nctxZrIDSnatbXRnVRaf1OO+LOAiduMDjfgYEsAXRQ=".getBytes());
    private final SecretKey refreshSecreteKey = Keys.hmacShaKeyFor("/mctxZrIDSnatbXRnVRaf1OO+LOAiduMDjfgYEsAXRQ=".getBytes());

    private SecretKey secretKey = accessSecreteKey; //= Keys.hmacShaKeyFor("/actxZrIDSnatbXRnVRaf1OO+LOAiduMDjfgYEsAXRQ=".getBytes());

    public String type = "";

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String generateJwtAccessToken(String userId) {
        System.out.println(userId);
        System.out.println(userRepo);
        Users users = userRepo.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        List<String> roles = users.getRoles().stream().map(role -> role.getRole()).toList();

        Map<String, Object> claims = new HashMap<>();
        claims.put("iss", "Task Manager App");
//        claims.put("type", "access");
        claims.put("role", roles);

        return Jwts.builder()
                .claims()
                .add(claims)
                .subject(userId)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 10))
                .and()
                .signWith(accessSecreteKey)
                .compact();
    }

    public String generateJwtRefreshToken(String userId) {

        Users users = userRepo.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        List<String> roles = users.getRoles().stream().map(role -> role.getRole()).toList();

        Map<String, Object> claims = new HashMap<>();
        claims.put("iss", "Task Manager App");
//        claims.put("type", "refresh");
        claims.put("role", roles);

        return Jwts.builder()
                .claims()
                .add(claims)
                .subject(userId)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 1000L * 60 * 60 * 24 * 30))
                .and()
                .signWith(refreshSecreteKey)
                .compact();
    }

    private Claims extractAllClaims(String token) {

        if ("access".equalsIgnoreCase(type)){
            secretKey = accessSecreteKey;
            System.out.println("access type validation");
        }else {
            secretKey = refreshSecreteKey;
            System.out.println("refresh type validation");
        }

        return Jwts
                .parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    private <T> T extractClaims(String token, Function<Claims, T> claimResolver) {
        final Claims claims = extractAllClaims(token);
        return claimResolver.apply(claims);
    }

    public String extractUsername(String token) {
        return extractClaims(token, Claims::getSubject);
    }

    public boolean validateToken(String token, UserDetails userDetails) {

//        if ("refresh".equalsIgnoreCase(type)){
//            secretKey = refreshSecreteKey;
//            System.out.println("access type validation");
//        }else {
//            secretKey = accessSecreteKey;
//            System.out.println("refresh type validation");
//        }
//            System.out.println("secrete key" + secretKey);
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    public boolean isTokenExpired(String token) {
        return extractExpireDate(token).before(new Date());
    }

    public Date extractExpireDate(String token) {
        return extractClaims(token, Claims::getExpiration);
    }

//    public boolean isAccessToken(String token) {
//        String type = (String) Jwts.parser().verifyWith(accessSecreteKey)
//                .build()
//                .parseSignedClaims(token)
//                .getPayload().get("type");
//        return "access".equals(type);
//    }
}
