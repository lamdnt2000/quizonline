package com.quizonline.group8.controller;

import com.quizonline.group8.authority.MemberDetails;
import com.quizonline.group8.authority.MemberServiceApp;
import com.quizonline.group8.common.Constants;
import com.quizonline.group8.dto.LoginDTO;
import com.quizonline.group8.dto.LoginReponseDTO;
import com.quizonline.group8.dto.RegisterDTO;
import com.quizonline.group8.dto.ResponseDTO;
import com.quizonline.group8.jwt.JwtConfig;
import com.quizonline.group8.model.Member;
import com.quizonline.group8.service.MemberServices;
import com.quizonline.group8.service.impl.MemberServiceImpl;
import com.quizonline.group8.utils.CookieUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.PermitAll;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

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
    @Autowired
    private MemberServiceApp memberServiceApp;

    public LoginController(AuthenticationManager authenticationManager, MemberServiceImpl memberService, JwtConfig jwtConfig, BCryptPasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.memberService = memberService;
        this.jwtConfig = jwtConfig;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping(value = "/authenticate",consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @PermitAll
    public ResponseEntity<ResponseDTO> Login(@Valid LoginDTO loginDTO, HttpServletResponse response){
        ResponseDTO reponseDTO = new ResponseDTO();
        try {
            Authentication authentication = new UsernamePasswordAuthenticationToken(loginDTO.getEmail(),loginDTO.getPassword());
            Authentication authenticate = authenticationManager.authenticate(authentication);
            if(authenticate.isAuthenticated()){
                Member member = memberService.findByEmail(authenticate.getName());
                    String token = jwtConfig.generateJwt(authenticate,member);
                    LoginReponseDTO loginReponseDTO = LoginReponseDTO.builder()
                            .email(member.getEmail())
                            .fullname(member.getFullname())
                            .role(member.getRoles().getRoleName())
                            .status(member.getStatus())
                            .token(jwtConfig.getTokenPrefix() + token)
                            .build();
                    reponseDTO.setData(loginReponseDTO);
                    reponseDTO.setSuccessCode(Constants.SUCCESS_CODE);
                CookieUtil.create(response,"token",token,true,86400);

            }
            else {
                reponseDTO.setErrorCode(Constants.FAIL_CODE);
                reponseDTO.setData(new String("TRY AGAIN"));
            }
        }
        catch (BadCredentialsException e){
            reponseDTO.setErrorCode(Constants.FAIL_CODE);
            reponseDTO.setData("User not founds");
        }
        catch (Exception e){
            reponseDTO.setErrorCode(Constants.FAIL_CODE);
            reponseDTO.setData(new String(e.getMessage()));
        }
        finally {
            return ResponseEntity.ok().body(reponseDTO);
        }
    }

    @PostMapping(value="/register",consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @PermitAll
    public ResponseEntity<ResponseDTO> register(@Valid RegisterDTO registerDTO){
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

    @GetMapping("/info")
    @PermitAll
    public  ResponseEntity<MemberDetails> getInfor(){
        Authentication authenticate = SecurityContextHolder.getContext().getAuthentication();
        MemberDetails memberDetails = null;
        if (authenticate.isAuthenticated()) {
            memberDetails = (MemberDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        }
        return ResponseEntity.ok(memberDetails);
    }
}
