package com.spring_greens.presentation.auth.security.handler;

import java.io.IOException;

import java.util.Map;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring_greens.presentation.auth.dto.TokenDto;
import com.spring_greens.presentation.auth.repository.MemberRepository;
import com.spring_greens.presentation.auth.security.Jwt.JwtTokenProvider;
import com.spring_greens.presentation.auth.service.MemberServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class customSuccesshandler implements AuthenticationSuccessHandler{

    private static final Logger logger = LoggerFactory.getLogger(customSuccesshandler.class);

    private final JwtTokenProvider jwtTokenProvider;
    
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
        Authentication authentication) throws IOException, ServletException {

            logger.info("성공 핸들러 사용");

            TokenDto tokens = jwtTokenProvider.generateToken(authentication);
            String accessToken = tokens.getAccessToken();
            String refreshToken = tokens.getRefreshToken();

            Cookie[] cookies = request.getCookies();
            if(cookies != null){
                for (int i = 0; i < cookies.length; i++){
                    cookies[i].setMaxAge(0);
                    response.addCookie(cookies[i]);
                }
            }


            Cookie cookie = new Cookie("refreshToken", refreshToken);
            cookie.setPath("/");
            cookie.setMaxAge(60*60*24);
            //js 접근 제한
            cookie.setHttpOnly(true);
            //https에서만 전송
            cookie.setSecure(true);
            response.addCookie(cookie);

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.setStatus(HttpServletResponse.SC_OK);

            Map<String, Object> reponseBody = new HashMap<>();
            reponseBody.put("accessToken", accessToken);

            response.getWriter().write(new ObjectMapper().writeValueAsString(reponseBody));
            response.getWriter().flush();



            
    }
}
