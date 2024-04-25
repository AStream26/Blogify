package com.app.blog.service.impl;

import com.app.blog.Constant.AppRole;
import com.app.blog.config.JwtTokenProvider;
import com.app.blog.dtos.LoginDto;
import com.app.blog.dtos.UserDetails;
import com.app.blog.dtos.UserDto;
import com.app.blog.entity.Author;
import com.app.blog.entity.Role;
import com.app.blog.exception.BlogAPIException;
import com.app.blog.repository.IAuthorRepository;
import com.app.blog.repository.IRoleRepository;
import com.app.blog.service.IAuthService;
import com.app.blog.utils.ObjectMapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class AuthServiceImpl implements IAuthService {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private IAuthorRepository authorRepositery;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private IRoleRepository roleRepository;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Override
    public String login(LoginDto loginDto) {
        System.out.println("AuthServiceImpl.login");
        if (!authorRepositery.existsByUsernameOrEmail(loginDto.getUsernameOrEmail(), loginDto.getUsernameOrEmail()))
            throw new BlogAPIException("UserName or email does not " +
                    "exists", HttpStatus.BAD_REQUEST);
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsernameOrEmail(), loginDto.getPassword()));
        UserDetails details = (UserDetails)authentication.getPrincipal();
        System.out.println("authentication = " + details);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return jwtTokenProvider.generateJwtToken(authentication);
    }

    @Override
    public String register(UserDto registerDto) throws Exception {
        System.out.println("AuthServiceImpl.register");

        if (authorRepositery.existsByUsername(registerDto.getUsername()))
            throw new BlogAPIException("UserName already exists", HttpStatus.BAD_REQUEST);

        if (authorRepositery.existsByEmail(registerDto.getEmail()))
            throw new BlogAPIException("Email already exists", HttpStatus.BAD_REQUEST);
        Author author = ObjectMapperUtils.mapEntity(registerDto,Author.class);
        author.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        Set<Role> roles = new HashSet<>();
        Role userRole = roleRepository.findByRole(AppRole.ROLE_ADMIN.toString()).orElseThrow(() -> new Exception("server error"));
        System.out.println("registerDto = " + userRole);
        roles.add(userRole);
        author.setRoles(roles);
        Author savedAuthor = authorRepositery.save(author);
        return "author registered successfully";
    }
}
