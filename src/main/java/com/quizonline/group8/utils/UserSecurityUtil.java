package com.quizonline.group8.utils;

import com.quizonline.group8.authority.MemberDetails;
import com.quizonline.group8.model.Member;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;

public class UserSecurityUtil {
    public static Member getCurrentUser(){
        Authentication authenticate = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(authenticate.getAuthorities());
        if (authenticate.isAuthenticated()){
            MemberDetails memberDetails = (MemberDetails) authenticate.getPrincipal();
            return memberDetails.getMember();
        }
        return null;
    }

    public static String getRole(){
        Authentication authenticate = SecurityContextHolder.getContext().getAuthentication();
        List<SimpleGrantedAuthority> authorities = (List<SimpleGrantedAuthority>) authenticate.getAuthorities();
        SimpleGrantedAuthority authority = authorities.get(0);
        return authority.getAuthority();
    }
}
