package com.app.blog.config;


import com.app.blog.service.IUserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {

    private IUserService userService;

    @Bean
    BCryptPasswordEncoder getMethodEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {

        return config.getAuthenticationManager();
        // No need to provide user serviceImpl and password encoder from spring security 5.x it get it bt default
    }

    @Bean
    SecurityFilterChain customFilterChain(HttpSecurity http) throws Exception {

        http.csrf(AbstractHttpConfigurer::disable);
        return http.build();
    }
    /*
    @Bean
    UserDetailsService generateUserDetails(){
        UserDetails user1 = User.builder().username("avis").password(getMethodEncoder().encode("avis1")).roles("ADMIN").build();
        return new InMemoryUserDetailsManager(user1);
            We can check the role at controller level like /posts/:id  @PreAuthorize("hasRole('ADMIN')"), we have to add the role in
            spring security filter chain

     */
}
    */
            }
