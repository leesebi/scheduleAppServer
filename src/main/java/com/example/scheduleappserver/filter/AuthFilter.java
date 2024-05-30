package com.example.scheduleappserver.filter;

import com.example.scheduleappserver.entity.User;
import com.example.scheduleappserver.jwt.JwtUtil;
import com.example.scheduleappserver.repository.UserRepository;
import io.jsonwebtoken.Claims;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.IOException;

@Slf4j(topic = "AuthFilter")
@Component
@Order(2)
public class AuthFilter implements Filter {
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    public AuthFilter(UserRepository userRepository, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String url = httpServletRequest.getRequestURI();

        // 회원가입, 로그인 API는 인증 필요 X
        if (StringUtils.hasText(url) && (url.startsWith("/api/signup") || url.startsWith("/api/login"))) {
            chain.doFilter(request, response);
        } else {
            // 토큰이 있는지 확인
            String tokenValue = jwtUtil.getTokenFromRequest(httpServletRequest);

            if (StringUtils.hasText(tokenValue)) {
                String token = jwtUtil.substringToken(tokenValue);

                // 토큰 검증
                if (!jwtUtil.validateToken(token)) {
                    throw new IllegalArgumentException("token error");
                }

                // 토큰에서 사용자 정보 가져오기
                Claims info = jwtUtil.getUserInfoFromToken(token);

                User user = userRepository.findByUsername(info.getSubject()).orElseThrow(
                        () -> new NullPointerException("Not Found User")
                );

                request.setAttribute("user", user);

                chain.doFilter(request, response);
            }else{
                throw new IllegalArgumentException("Not Found Token");
            }
        }
    }
}
