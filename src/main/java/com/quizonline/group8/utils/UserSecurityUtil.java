package com.quizonline.group8.utils;

import com.quizonline.group8.authority.MemberDetails;
import com.quizonline.group8.model.Member;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class UserSecurityUtil {
    public final static Member getCurrentUser(){
        Authentication authenticate = SecurityContextHolder.getContext().getAuthentication();
        if (authenticate.isAuthenticated()){
            MemberDetails memberDetails = (MemberDetails) authenticate.getPrincipal();
            return memberDetails.getMember();
        }
        return null;
    }
}
