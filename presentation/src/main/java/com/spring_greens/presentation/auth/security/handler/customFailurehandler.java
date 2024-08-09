package com.spring_greens.presentation.auth.security.handler;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import com.spring_greens.presentation.auth.repository.MemberRepository;
import com.spring_greens.presentation.auth.service.MemberServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class customFailurehandler implements AuthenticationFailureHandler {

    private final Logger logger = LoggerFactory.getLogger(customFailurehandler.class);

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException exception) throws IOException, ServletException {

        //에러를 여기서 처리하는게 맞는건가 싶음.
        logger.info("실패함.");
    }
    
}
