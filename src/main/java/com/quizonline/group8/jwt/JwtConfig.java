package com.quizonline.group8.jwt;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.security.Keys;

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

    @Bean
    public javax.crypto.SecretKey secretKey(){
        return Keys.hmacShaKeyFor(secretKey.getBytes());
    }
}
