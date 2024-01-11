package br.com.acme.gestao_vagas.modules.candidate.useCases;

import java.util.UUID;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.acme.gestao_vagas.modules.candidate.CandidateRepository;
import br.com.acme.gestao_vagas.modules.candidate.dto.ProfileCandidateResponseDTO;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class ProfileCandidateUseCase {
    private CandidateRepository candidateRepository;

    public ProfileCandidateResponseDTO execute(UUID candidateId) {
        var candidate = this.candidateRepository.findById(candidateId)
                .orElseThrow(() -> {
                    throw new UsernameNotFoundException("User not found");
                });

        return ProfileCandidateResponseDTO.builder()
                .description(candidate.getDescription())
                .username(candidate.getUsername())
                .email(candidate.getEmail())
                .name(candidate.getName())
                .id(candidate.getId())
                .build();
    }

}
