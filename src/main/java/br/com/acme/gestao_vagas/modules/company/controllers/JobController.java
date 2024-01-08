package br.com.acme.gestao_vagas.modules.company.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.acme.gestao_vagas.modules.company.entities.JobEntity;
import br.com.acme.gestao_vagas.modules.company.useCases.CreateJobUseCase;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/job")
public class JobController {
    private CreateJobUseCase createJobUseCase;

    @PostMapping
    public ResponseEntity<JobEntity> create(@Valid @RequestBody JobEntity jobEntity) {
        var job = this.createJobUseCase.execute(jobEntity);
        return ResponseEntity.ok().body(job);
    }

}
