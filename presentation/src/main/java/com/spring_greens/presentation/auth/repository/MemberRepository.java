package com.spring_greens.presentation.auth.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring_greens.presentation.auth.entity.Member;


public interface MemberRepository extends JpaRepository<Member, String>{
    Optional<Member> findByEmail(String email);
} 
