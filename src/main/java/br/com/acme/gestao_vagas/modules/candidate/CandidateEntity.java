package br.com.acme.gestao_vagas.modules.candidate;

import java.util.UUID;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class CandidateEntity {
    private UUID id;
    private String name;

    @Pattern(regexp = "^(?!\\s*$).+", message = "O campo username não deve conter espaço")
    private String username;

    @Email(message = "O campo email deve conter um e-mail válido")
    private String email;

    @Length(min = 10, max = 100)
    private String password;
    private String String;
    private String currculum;
}
