package com.quizonline.group8.jwt;

import com.quizonline.group8.authority.MemberDetails;
import com.quizonline.group8.authority.MemberServiceApp;
import com.quizonline.group8.utils.CookieUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import lombok.AllArgsConstructor;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor

public class TokenVerifier extends OncePerRequestFilter {
    private SecretKey secretKey;
    private JwtConfig jwtConfig;
    @Autowired
    private MemberServiceApp memberServiceApp;



    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String token = CookieUtil.getValue(request,"token");
        if (token == null){
            filterChain.doFilter(request, response);
            return ;
        }

        try {

            Jws<Claims> claimsJws = Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token);
            Claims body = claimsJws.getBody();
            MemberDetails memberDetails = (MemberDetails) memberServiceApp.loadUserByUsername(body.getSubject());

            var authorities = (List<Map<String,String>>)body.get("authorities");
            Set<SimpleGrantedAuthority> simpleGrantedAuthorities = authorities.stream().map(m->new SimpleGrantedAuthority("ROLE_"+m.get("authority"))).collect(Collectors.toSet());//filler collect list map thì dùng stream
            Authentication authentication = new UsernamePasswordAuthenticationToken(memberDetails,null,simpleGrantedAuthorities);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }catch(JwtException e)
        {
            CookieUtil.clear(response,"token");
            throw new IllegalStateException("Token cannot be trust");
        }
        finally {
            filterChain.doFilter(request, response);
        }
    }



}
