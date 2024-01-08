package br.com.acme.gestao_vagas.modules.company.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class AuthCompanyDTO {
    private String username;
    private String password;

}
