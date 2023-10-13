package org.example.infra.Security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import org.example.Domain.Usuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    public String gerarToken(Usuario usuario) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            String token = JWT.create().withIssuer("vitae-servicos").
                    withSubject(usuario.getUsername()).
                    withExpiresAt(gerarTempoExpiracao())
                    .sign(algorithm);
            System.out.println("token gerado :" + token);
            return token;
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Error while generating token", exception);
        }
    }

    public String usuarioDoToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);

            System.out.println("token gerado para o user :" + token);
            return JWT.require(algorithm)
                    .withIssuer("vitae-servicos").build()
                    .verify(token).getSubject();
        } catch (JWTCreationException exception) {
            System.out.println("erro no token gerado para o user :" + token);
            return "";
        }
    }

    private Instant gerarTempoExpiracao() {
        return LocalDateTime.now().plusMinutes(5).toInstant(ZoneOffset.of("-03:00"));
    }
}
