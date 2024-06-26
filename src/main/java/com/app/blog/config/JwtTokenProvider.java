package com.app.blog.config;

import com.app.blog.exception.BlogAPIException;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtTokenProvider {

    @Value("${app.jwt-expiration-milliseconds}")
    private long expirationTime;

    @Value("${app.jwt-secret}")
    private String secretKey;


    public String generateJwtToken(Authentication authentication){

        String username = authentication.getName();

        Date currentTime = new Date();

        Date expiredAt = new Date(currentTime.getTime() + expirationTime);

        return Jwts.builder()
                            .setSubject(username)
                            .setIssuedAt(new Date())
                            .setExpiration(expiredAt)
                            .signWith(getKey())
                            .compact();
    }

    public Key getKey(){
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey));
    }

    public String getUserName(String token){

        return Jwts.parserBuilder()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean validateJWT(String token){

        try{
            Jwts.parserBuilder().setSigningKey(getKey()).build().parse(token);
            return true;
        }
        catch (MalformedJwtException exp){
            System.out.println("MalformedJwtException = " + exp.getMessage());
            throw new BlogAPIException("InValid JWT Token",HttpStatus.BAD_REQUEST);
        }
        catch (ExpiredJwtException exp){
            System.out.println("Expired JWT = " + exp.getMessage());
            throw new BlogAPIException("JWT Token Expired",HttpStatus.BAD_REQUEST);
        }
        catch (Exception exp){
            System.out.println("JwtTokenProvider.validateJWT");
            throw new BlogAPIException(exp.getMessage(), HttpStatus.UNAUTHORIZED);
        }
    }

}
