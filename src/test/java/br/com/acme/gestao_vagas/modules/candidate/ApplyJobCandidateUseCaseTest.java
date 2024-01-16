package br.com.acme.gestao_vagas.modules.candidate;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.assertj.core.api.Assertions.assertThat;

import br.com.acme.gestao_vagas.exceptions.JobNotFoundException;
import br.com.acme.gestao_vagas.exceptions.UserNotFoundException;
import br.com.acme.gestao_vagas.modules.candidate.entity.ApplyJobEntity;
import br.com.acme.gestao_vagas.modules.candidate.repository.ApplyJobRepository;
import br.com.acme.gestao_vagas.modules.candidate.useCases.ApplyJobCandidateUseCase;
import br.com.acme.gestao_vagas.modules.company.entities.JobEntity;
import br.com.acme.gestao_vagas.modules.company.repositories.JobRepository;

@ExtendWith(MockitoExtension.class)
public class ApplyJobCandidateUseCaseTest {
    @InjectMocks
    private ApplyJobCandidateUseCase applyJobCandidateUseCase;

    @Mock
    private CandidateRepository candidateRepository;

    @Mock
    private JobRepository jobRepository;

    @Mock
    private ApplyJobRepository applyJobRepository;

    @Test
    @DisplayName("Should not be able to apply to a job when candidate is not found")
    public void should_not_be_able_to_apply_job_when_candidate_not_found() {
        try {
            this.applyJobCandidateUseCase.execute(null, null);

        } catch (Exception e) {
            assertThat(e).isInstanceOf(UserNotFoundException.class);
        }
    }

    @Test
    @DisplayName("Should not be able to apply to a job when job is not found")
    public void should_not_be_able_to_apply_job_when_not_found() {
        var candidateId = UUID.randomUUID();
        var candidate = new CandidateEntity();
        candidate.setId(candidateId);

        when(candidateRepository.findById(
                candidateId))
                .thenReturn(Optional.of(candidate));

        try {
            this.applyJobCandidateUseCase.execute(candidateId, null);

        } catch (Exception e) {
            assertThat(e).isInstanceOf(JobNotFoundException.class);
        }
    }

    @Test
    @DisplayName("Should be able to create a new job application")
    public void should_be_able_to_create_a_new_job_application() {
        var idCandidate = UUID.randomUUID();
        var idJob = UUID.randomUUID();

        var applyJob = ApplyJobEntity.builder().candidateId(idCandidate)
                .jobId(idJob).build();

        var applyJobCreated = ApplyJobEntity.builder().id(UUID.randomUUID()).build();

        when(candidateRepository.findById(idCandidate)).thenReturn(Optional.of(new CandidateEntity()));
        when(jobRepository.findById(idJob)).thenReturn(Optional.of(new JobEntity()));

        when(applyJobRepository.save(applyJob)).thenReturn(applyJobCreated);

        var result = applyJobCandidateUseCase.execute(idCandidate, idJob);

        assertThat(result).hasFieldOrProperty("id");
        assertNotNull(result.getId());
    }

}
