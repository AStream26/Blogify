package com.app.blog.service.impl;

import com.app.blog.entity.Author;
import com.app.blog.repository.IAuthorRepositery;
import com.app.blog.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    IAuthorRepositery authorRepositery;

    @Override
    public UserDetails loadUserByUsername(String userNameOrEmail) throws UsernameNotFoundException {
        Author user = authorRepositery.findByUsernameOrEmail(userNameOrEmail, userNameOrEmail).orElseThrow(() -> new UsernameNotFoundException("username or email does not exist"));
        System.out.println("UserServiceImpl.loadUserByUsername");
        Set<GrantedAuthority> authorities = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.toString()))
                .collect(Collectors.toSet());
        return new com.app.blog.dtos.UserDetails(user.getUsername(),user.getPassword(),true,true,
                true,true,authorities,user.getId(),user.getEmail(),user.getName());
    }
}
