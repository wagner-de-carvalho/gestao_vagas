package br.com.acme.gestao_vagas.modules.candidate.useCases;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.acme.gestao_vagas.exceptions.UserFoundException;
import br.com.acme.gestao_vagas.modules.candidate.CandidateEntity;
import br.com.acme.gestao_vagas.modules.candidate.CandidateRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class CreateCandidateUseCase {
    private CandidateRepository candidateRepository;
    private PasswordEncoder passwordEncoder;

    public CandidateEntity execute(CandidateEntity candidate) {
        this.candidateRepository
                .findByUsernameOrEmail(candidate.getUsername(), candidate.getEmail())
                .ifPresent(user -> {
                    throw new UserFoundException();
                });
        var password = this.passwordEncoder.encode(candidate.getPassword());
        candidate.setPassword(password);
        return this.candidateRepository.save(candidate);
    }

}
