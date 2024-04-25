package com.app.blog.config;


import com.app.blog.service.IUserService;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.catalina.connector.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@SecurityScheme(
        name = "Bearer Authorization",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        scheme = "bearer"
)
public class SpringSecurityConfig {

    @Autowired
    private IUserService userService;
    @Autowired
    private BCryptPasswordEncoder encoder;
    @Autowired
    private AuthenticationEntryPoint authenticationEntryPoint;
    @Autowired
    private JwtAuthenticationFilter authenticationFilter;

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    SecurityFilterChain customFilterChain(HttpSecurity http) throws Exception {

        System.out.println("SpringSecurityConfig.customFilterChain");
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((request)->request.requestMatchers(HttpMethod.GET,"/api/v1/posts/**","/api/v1/category/**",
                                "/swagger-ui/**","/v3/api-docs/**")
                .permitAll())
                .authorizeHttpRequests((request)->request.requestMatchers("/api/v1/auth/**").permitAll())
                .authorizeHttpRequests((request)->request.anyRequest().authenticated())
                .addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling((exception)->exception.authenticationEntryPoint(authenticationEntryPoint))
                        .sessionManagement((session)->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
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
