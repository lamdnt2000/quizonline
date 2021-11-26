package com.quizonline.group8.service;

import com.quizonline.group8.dto.RegisterDTO;
import com.quizonline.group8.model.Member;
import com.quizonline.group8.model.Role;
import com.quizonline.group8.repository.MemberRepository;
import com.quizonline.group8.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class MemberServices {
    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepository roleRepository;

    public void save(RegisterDTO registerDTO) {
        Member member = new Member();
        member.setEmail(registerDTO.getEmail());
        member.setPassword(passwordEncoder.encode(registerDTO.getPassword()));
        member.setFullname(registerDTO.getFullname());
        Role role = roleRepository.findByRoleName("Student");
        member.setRoles(role);
        memberRepository.save(member);
    }
}
