package br.com.acme.gestao_vagas.modules.company.useCases;

import org.springframework.stereotype.Service;

import br.com.acme.gestao_vagas.modules.company.entities.JobEntity;
import br.com.acme.gestao_vagas.modules.company.repositories.JobRepository;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.NonNull;

@AllArgsConstructor
@Service
public class CreateJobUseCase {
    private JobRepository jobRepository;

    public JobEntity execute(@Valid @NonNull JobEntity jobEntity) {
        return this.jobRepository.save(jobEntity);
    }

}
