package br.com.acme.gestao_vagas.modules.candidate.useCases;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;

import javax.security.sasl.AuthenticationException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import br.com.acme.gestao_vagas.modules.candidate.CandidateRepository;
import br.com.acme.gestao_vagas.modules.candidate.dto.AuthCandidateRequestDTO;
import br.com.acme.gestao_vagas.modules.candidate.dto.AuthCandidateResponseDTO;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class AuthCandidateUseCase {
    @Value("${security.token.secret.candidate}")
    private String secretKey;

    private CandidateRepository candidateRepository;
    private PasswordEncoder passwordEncoder;

    public AuthCandidateResponseDTO execute(AuthCandidateRequestDTO authCandidateRequestDTO)
            throws AuthenticationException {
        var candidate = this.candidateRepository
                .findByUsername(authCandidateRequestDTO.username())
                .orElseThrow(() -> {
                    throw new UsernameNotFoundException("Username/password incorrect");
                });

        var passwordMatches = this.passwordEncoder
                .matches(authCandidateRequestDTO.password(), candidate.getPassword());
        if (!passwordMatches) {
            throw new AuthenticationException();
        }

        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        var token = JWT.create()
                .withIssuer("javagas")
                .withSubject(candidate.getId().toString())
                .withClaim("roles", Arrays.asList("candidate"))
                .withExpiresAt(Instant.now().plus(Duration.ofHours(10)))
                .sign(algorithm);

        return AuthCandidateResponseDTO.builder()
                .access_token(token)
                .build();
    }

}
