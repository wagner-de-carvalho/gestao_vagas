package br.com.acme.gestao_vagas.modules.company.useCases;

import org.springframework.stereotype.Service;

import br.com.acme.gestao_vagas.exceptions.UserFoundException;
import br.com.acme.gestao_vagas.modules.company.entities.CompanyEntity;
import br.com.acme.gestao_vagas.modules.company.repositories.CompanyRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class CreateCompanyUseCase {
    private CompanyRepository companyRepository;

    public CompanyEntity execute(CompanyEntity companyEntity) {
        this.companyRepository
                .findByUsernameOrEmail(companyEntity.getUsername(), companyEntity.getEmail())
                .ifPresent(company -> {
                    throw new UserFoundException("Empresa já cadastrada");
                });
        return this.companyRepository.save(companyEntity);
    }

}
