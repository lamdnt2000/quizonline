package com.quizonline.group8.authority;

import com.quizonline.group8.model.Member;
import com.quizonline.group8.model.Role;
import com.quizonline.group8.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceApp implements UserDetailsService {

    @Autowired
    private MemberRepository memberRepository;

//    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Member member = memberRepository.findByEmail(s);
        if(member == null){
            throw new UsernameNotFoundException("Member not found!!!");
        }
        return new MemberDetails(member);
    }
}
