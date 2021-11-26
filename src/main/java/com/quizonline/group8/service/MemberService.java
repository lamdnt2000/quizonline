package com.quizonline.group8.service;

import com.quizonline.group8.model.Member;

public interface MemberService {
    Member findByEmail(String email);
}
