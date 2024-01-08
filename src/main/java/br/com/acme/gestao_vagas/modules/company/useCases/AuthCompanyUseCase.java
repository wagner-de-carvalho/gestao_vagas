package br.com.acme.gestao_vagas.modules.company.useCases;

import javax.naming.AuthenticationException;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.acme.gestao_vagas.modules.company.dto.AuthCompanyDTO;
import br.com.acme.gestao_vagas.modules.company.repositories.CompanyRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class AuthCompanyUseCase {
    private CompanyRepository companyRepository;
    private PasswordEncoder passwordEncoder;

    public void execute(AuthCompanyDTO authCompanyDTO) throws AuthenticationException {
        var company = this.companyRepository
                .findByUsername(authCompanyDTO.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("Company not found"));

        var passwordMatches = this.passwordEncoder.matches(authCompanyDTO.getPassword(), company.getPassword());
        if (!passwordMatches) {
            throw new AuthenticationException();
        }
    }

}
