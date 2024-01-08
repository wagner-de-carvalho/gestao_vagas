package br.com.acme.gestao_vagas.modules.company.useCases;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.acme.gestao_vagas.exceptions.UserFoundException;
import br.com.acme.gestao_vagas.modules.company.entities.CompanyEntity;
import br.com.acme.gestao_vagas.modules.company.repositories.CompanyRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class CreateCompanyUseCase {
    private CompanyRepository companyRepository;

    private PasswordEncoder passwordEncoder;

    public CompanyEntity execute(CompanyEntity companyEntity) {
        this.companyRepository
                .findByUsernameOrEmail(companyEntity.getUsername(), companyEntity.getEmail())
                .ifPresent(company -> {
                    throw new UserFoundException("Empresa já cadastrada");
                });
        var password = passwordEncoder.encode(companyEntity.getPassword());
        companyEntity.setPassword(password);
        return this.companyRepository.save(companyEntity);
    }

}
