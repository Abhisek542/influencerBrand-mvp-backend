package com.abhi.influencermvp.config;

import com.abhi.influencermvp.enums.Role;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class JwtAuthFilter implements Filter {

    private final JwtUtil jwtUtil;

    public JwtAuthFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        String authHeader = req.getHeader("Authorization");

        // Allow login & register without token
        if (req.getRequestURI().startsWith("/api/auth")) {
            chain.doFilter(request, response);
            return;
        }

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {

            unauthorized(response,"Missing or Invalid token");
            return;
        }
        String token = authHeader.substring(7);

        if (!jwtUtil.validateToken(token)) {
            unauthorized(response, "Invalid Token");
            return;
        }
        String email = jwtUtil.extractEmailFromToken(token);
        Role role = jwtUtil.extractRoleFromToken(token);

        UsernamePasswordAuthenticationToken auth =
                new UsernamePasswordAuthenticationToken(email, null, List.of(()->"ROLE_"+role.name()));

        SecurityContextHolder.getContext().setAuthentication(auth);

        chain.doFilter(request, response);


        }
    private void unauthorized(ServletResponse response, String message) throws IOException {
        HttpServletResponse res = (HttpServletResponse) response;
        res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        res.getWriter().write(message);
    }


    }


