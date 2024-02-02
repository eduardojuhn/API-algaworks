package com.algaworks.algalog.service;

import com.algaworks.algalog.model.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${api.jwt.token}")
    public String generateToken(User user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256("secret");
            String token = JWT.create()
                    .withIssuer("auth-api")
                    .withSubject(user.getUsername().toString())
                    .withExpiresAt(genExpirationDate())
                    .sign(algorithm);
            return token;
        } catch (JWTCreationException e) {
            throw new RuntimeException("Erro ao gerar token", e);
        }
    }

   public String validateToken(String token) {
       try {
           Algorithm algorithm = Algorithm.HMAC256("secret");
           return JWT.require(algorithm)
                   .withIssuer("auth-api")
                   .build()
                   .verify(token)
                   .getSubject();
       } catch (Exception e) {
           throw new RuntimeException("Token inválido", e);
       }
   }

    private Instant genExpirationDate() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.UTC);
    }
}
