package com.spring_greens.presentation.auth.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring_greens.presentation.auth.dto.LoginDto;
import com.spring_greens.presentation.auth.dto.MemberDto;
import com.spring_greens.presentation.auth.dto.SignUpDto;
import com.spring_greens.presentation.auth.dto.TokenDto;
import com.spring_greens.presentation.auth.service.MemberService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;



@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberContoller {
    
    private final MemberService memberService;

    @PostMapping("/login")
    public TokenDto login(@RequestBody LoginDto loginDTO) {
        TokenDto tokenDTO = memberService.login(loginDTO);
        log.info("request email = {}, password = {}", loginDTO.getEmail(), loginDTO.getPassword());
        log.info("jwtToken accessToken = {}, refreshToken = {}", tokenDTO.getAccessToken(), tokenDTO.getRefreshToken());
        
        return tokenDTO;
    }

    @PostMapping("/test")
    public String test() {
        return "success";
    }

    @PostMapping("/signup")
    public ResponseEntity<MemberDto> signUp(@RequestBody SignUpDto signUpDto){
        MemberDto savedMemberDto = memberService.signUp(signUpDto);
        return ResponseEntity.ok(savedMemberDto);
    }
    
}
