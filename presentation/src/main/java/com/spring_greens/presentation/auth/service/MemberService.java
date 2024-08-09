package com.spring_greens.presentation.auth.service;

import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;

import com.spring_greens.presentation.auth.dto.LoginDto;
import com.spring_greens.presentation.auth.dto.MemberDto;
import com.spring_greens.presentation.auth.dto.SignUpDto;
import com.spring_greens.presentation.auth.dto.TokenDto;



public interface MemberService {
    public TokenDto login(LoginDto loginDto);
    public MemberDto signUp(SignUpDto signUpDto);
}