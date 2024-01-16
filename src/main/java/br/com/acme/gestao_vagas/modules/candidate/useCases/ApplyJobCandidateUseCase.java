package br.com.acme.gestao_vagas.modules.candidate.useCases;

import java.util.UUID;

import org.springframework.stereotype.Service;

import br.com.acme.gestao_vagas.exceptions.JobNotFoundException;
import br.com.acme.gestao_vagas.exceptions.UserNotFoundException;
import br.com.acme.gestao_vagas.modules.candidate.CandidateRepository;
import br.com.acme.gestao_vagas.modules.candidate.entity.ApplyJobEntity;
import br.com.acme.gestao_vagas.modules.candidate.repository.ApplyJobRepository;
import br.com.acme.gestao_vagas.modules.company.entities.JobEntity;
import br.com.acme.gestao_vagas.modules.company.repositories.JobRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class ApplyJobCandidateUseCase {
    private CandidateRepository candidateRepository;
    private JobRepository jobRepository;
    private ApplyJobRepository applyJobRepository;

    public ApplyJobEntity execute(UUID candidateId, UUID jobId) {
        this.candidateRepository.findById(candidateId)
                .orElseThrow(() -> {
                    throw new UserNotFoundException("User not found");
                });

        this.jobRepository.findById(jobId)
                .orElseThrow(() -> {
                    throw new JobNotFoundException("Job not found");
                });

        var applyJob = ApplyJobEntity.builder()
                .candidateId(candidateId)
                .jobId(jobId)
                .build();

        return this.applyJobRepository.save(applyJob);
    }

}
