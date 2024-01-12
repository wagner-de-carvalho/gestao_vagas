package br.com.acme.gestao_vagas.modules.candidate.controllers;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.acme.gestao_vagas.modules.candidate.CandidateEntity;
import br.com.acme.gestao_vagas.modules.candidate.useCases.CreateCandidateUseCase;
import br.com.acme.gestao_vagas.modules.candidate.useCases.ProfileCandidateUseCase;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/candidate")
public class CandidateController {

    private CreateCandidateUseCase createCandidateUseCase;
    private ProfileCandidateUseCase profileCandidateUseCase;

    @PostMapping
    public ResponseEntity<Object> create(@Valid @RequestBody CandidateEntity candidate) {
        try {
            var result = this.createCandidateUseCase.execute(candidate);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @GetMapping
    public ResponseEntity<Object> get(HttpServletRequest request) {
        var candidateId = UUID.fromString(request.getAttribute("candidate_id").toString());

        try {
            var result = this.profileCandidateUseCase.execute(candidateId);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
