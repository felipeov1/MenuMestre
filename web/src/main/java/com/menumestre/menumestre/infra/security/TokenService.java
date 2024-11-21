package com.menumestre.menumestre.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.menumestre.menumestre.domain.user.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("web.security.token.secret")
    private String secret;
    public String generateToken(User user){
        try{
            Algorithm algorithm = Algorithm.HMAC256(secret);

            String token = JWT.create()
                    .withIssuer("menuMestre")
                    .withSubject(user.getEmail())
                    .withExpiresAt(gentExpirationDate())
                    .sign(algorithm);

            return token;
        }
        catch(Exception exception ){
            throw new RuntimeException("Error generating token", exception);
        }
    }

    public String validateToken(String token){
        try{
            Algorithm algorithm = Algorithm.HMAC256(secret);

            return JWT.require(algorithm)
                    .withIssuer("menuMestre")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (Exception exception) {

            System.out.println("Error on validate token");
            return "";

        }
    }


    private Instant gentExpirationDate(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }

}
