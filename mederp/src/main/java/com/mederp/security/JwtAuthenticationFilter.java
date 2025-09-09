package com.mederp.security;

import java.io.IOException;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        // final String authHeader = request.getHeader(ALREADY_FILTERED_SUFFIX);

        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);


        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);

            
            if (jwtUtil.validateToken(token)) {
                String username = jwtUtil.extractUsername(token);
                String role = jwtUtil.extractRole(token);

                // UsernamePasswordAuthenticationToken authenticationToken =
                //     new UsernamePasswordAuthenticationToken(username, null, Collections.emptyList());
                UsernamePasswordAuthenticationToken authenticationToken =
                                                new UsernamePasswordAuthenticationToken(
                                                    username,   
                                                    null,
                                                    List.of(new SimpleGrantedAuthority("ROLE_" + role))
                                                );
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                
            }
            
        }
        filterChain.doFilter(request, response);
    }

}
