package com.quizonline.group8.service;

import com.quizonline.group8.model.Member;
import com.quizonline.group8.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class MemberDetailService implements UserDetailsService {
    @Autowired
    private MemberRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Member member = userRepository.findByEmail(email);
        if (member == null){
            throw new UsernameNotFoundException("Could not found user");
        }
        return new MemberDetails(member);
    }
}
