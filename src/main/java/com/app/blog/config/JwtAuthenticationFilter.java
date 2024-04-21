package com.app.blog.config;

import com.app.blog.dtos.UserDetails;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private UserDetailsService userDetailsService;
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        System.out.println("JwtAuthenticationFilter.doFilterInternal");
        String jwtToken =  getToken(request);

        if(StringUtils.hasText(jwtToken) && jwtTokenProvider.validateJWT(jwtToken)){

            String username = jwtTokenProvider.getUserName(jwtToken);

            UserDetails userDetails = (UserDetails) userDetailsService.loadUserByUsername(username);

            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails,null,
                    userDetails.getAuthorities());
            
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            //WebAuthenticationDetailsSource stores session id and IP

            SecurityContextHolder.getContext().setAuthentication(authentication);

            UserDetails details = (UserDetails) authentication.getPrincipal();
        }

        System.out.println("JwtAuthenticationFilter.doFilterInternal exit");

        filterChain.doFilter(request,response);

    }


    private  String getToken(HttpServletRequest request){

        String authorization = request.getHeader("Authorization");

        if(StringUtils.hasText(authorization) && authorization.startsWith("Bearer ")){
            return authorization.substring(7);
        }
        return null;
    }

}
