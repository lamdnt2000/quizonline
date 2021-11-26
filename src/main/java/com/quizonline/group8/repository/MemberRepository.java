package com.quizonline.group8.repository;

import com.quizonline.group8.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member,String> {
    Member findByEmail(@Param("email") String email);
}
