package com.quizonline.group8.controller;

import com.quizonline.group8.common.Constants;
import com.quizonline.group8.dto.LoginDTO;
import com.quizonline.group8.dto.LoginReponseDTO;
import com.quizonline.group8.dto.RegisterDTO;
import com.quizonline.group8.dto.ResponseDTO;
import com.quizonline.group8.jwt.JwtConfig;
import com.quizonline.group8.model.Member;
import com.quizonline.group8.service.MemberServiceImpl;
import com.quizonline.group8.service.MemberServices;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.annotation.security.PermitAll;
import javax.validation.Valid;
import java.time.LocalDate;
import java.util.Date;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/user")
public class LoginController {
    private AuthenticationManager authenticationManager;
    private MemberServiceImpl memberService;
    private JwtConfig jwtConfig;
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private MemberServices memberServices;

    public LoginController(AuthenticationManager authenticationManager, MemberServiceImpl memberService, JwtConfig jwtConfig, BCryptPasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.memberService = memberService;
        this.jwtConfig = jwtConfig;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/authenticate")
    @PermitAll
    public ResponseEntity<ResponseDTO> Login(@Valid @RequestBody LoginDTO loginDTO){
        ResponseDTO reponseDTO = new ResponseDTO();
        Authentication authentication = new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authentication);
        try {
            if(authenticate.isAuthenticated()){
                Member member = memberService.findByEmail(authenticate.getName());
                String token = Jwts.builder().setSubject(authenticate.getName())
                        .claim("authorities",authenticate.getAuthorities()).claim("email",member.getEmail())
                        .setIssuedAt((new Date())).setExpiration(java.sql.Date.valueOf(LocalDate.now().plusDays(1)))
                        .signWith(jwtConfig.secretKey()).compact();
                LoginReponseDTO loginReponseDTO = LoginReponseDTO.builder()
                        .email(member.getEmail())
                        .fullname(member.getFullname())
                        .role(member.getRoles().getRoleName())
                        .status(member.getStatus())
                        .token(jwtConfig.getTokenPrefix() + token)
                        .build();
                reponseDTO.setData(loginReponseDTO);
                reponseDTO.setSuccessCode(Constants.SUCCESS_CODE);
                return ResponseEntity.ok().body(reponseDTO);
            }
            else {
                reponseDTO.setErrorCode(Constants.FAIL_CODE);
                return ResponseEntity.ok().body(reponseDTO);
            }
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/register")
    @PermitAll
    public ResponseEntity<ResponseDTO> register(@RequestBody RegisterDTO registerDTO){
        ResponseDTO reponseDTO = new ResponseDTO();
        if(registerDTO != null){
            memberServices.save(registerDTO);
            reponseDTO.setSuccessCode(Constants.SUCCESS_CODE);
            return ResponseEntity.ok().body(reponseDTO);
        }
        else{
            reponseDTO.setErrorCode(Constants.FAIL_CODE);
            return ResponseEntity.ok().body(reponseDTO);
        }
    }
}
