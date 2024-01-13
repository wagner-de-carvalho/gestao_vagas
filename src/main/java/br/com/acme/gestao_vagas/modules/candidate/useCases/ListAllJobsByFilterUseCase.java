package br.com.acme.gestao_vagas.modules.candidate.useCases;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.acme.gestao_vagas.modules.company.entities.JobEntity;
import br.com.acme.gestao_vagas.modules.company.repositories.JobRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class ListAllJobsByFilterUseCase {
    private JobRepository jobRepository;

    public List<JobEntity> execute(String filter) {
        return this.jobRepository.findByDescriptionContainingIgnoreCase(filter);
    }

}
