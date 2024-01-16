package br.com.acme.gestao_vagas.modules.candidate;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import java.util.UUID;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.assertj.core.api.Assertions.assertThat;

import br.com.acme.gestao_vagas.exceptions.UserNotFoundException;
import br.com.acme.gestao_vagas.modules.candidate.useCases.ApplyJobCandidateUseCase;
import br.com.acme.gestao_vagas.modules.company.repositories.JobRepository;

@ExtendWith(MockitoExtension.class)
public class ApplyJobCandidateUseCaseTest {
    @InjectMocks
    private ApplyJobCandidateUseCase applyJobCandidateUseCase;

    @Mock
    private CandidateRepository candidateRepository;

    @Mock
    private JobRepository jobRepository;

    @Test
    @DisplayName("Should not be able to apply to a job when candidate is not found")
    public void should_not_be_able_to_apply_job_when_candidate_not_found() {
        try {
            this.applyJobCandidateUseCase.execute(UUID.randomUUID(), UUID.randomUUID());

        } catch (Exception e) {
            assertThat(e).isInstanceOf(UserNotFoundException.class);
        }
    }

}
