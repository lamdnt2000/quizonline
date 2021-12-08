package com.quizonline.group8.service.impl;

import com.quizonline.group8.model.Member;
import com.quizonline.group8.repository.MemberRepository;
import com.quizonline.group8.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService {
    @Autowired
    private MemberRepository memberRepository;

    @Override
    public Member findByEmail(String email) {
        return memberRepository.findByEmail(email);
    }


}
