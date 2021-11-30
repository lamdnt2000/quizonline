package com.quizonline.group8.jwt;

import com.quizonline.group8.model.Member;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Date;

@ConfigurationProperties(prefix = "application.jwt")
@AllArgsConstructor
@NoArgsConstructor
@Component
@Data
public class JwtConfig {
    private String secretKey;
    private String tokenPrefix;
    private int tokenExpirationAfterDays;

    public String getAuthorizationHeader(){
        return HttpHeaders.AUTHORIZATION;
    }

    public javax.crypto.SecretKey secretKey(){
        return Keys.hmacShaKeyFor(secretKey.getBytes());
    }

    public String generateJwt(Authentication authenticate, Member member){
        return Jwts.builder().setSubject(authenticate.getName())
                .claim("authorities", authenticate.getAuthorities()).claim("email", member.getEmail())
                .setIssuedAt((new Date())).setExpiration(java.sql.Date.valueOf(LocalDate.now().plusDays(1)))
                .signWith(this.secretKey()).compact();
    }

}
