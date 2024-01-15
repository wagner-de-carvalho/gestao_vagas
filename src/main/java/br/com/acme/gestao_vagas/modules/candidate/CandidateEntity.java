package br.com.acme.gestao_vagas.modules.candidate;

import java.util.UUID;

import org.hibernate.validator.constraints.Length;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Entity(name = "candidate")
public class CandidateEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Schema(example = "Java Acme", requiredMode = RequiredMode.REQUIRED)
    private String name;

    @Pattern(regexp = "\\S+", message = "O campo username não deve conter espaços")
    @Schema(example = "javaacme", requiredMode = RequiredMode.REQUIRED)
    private String username;

    @Email(message = "O campo email deve conter um e-mail válido")
    @Schema(example = "javaacme@mail.com", requiredMode = RequiredMode.REQUIRED)
    private String email;

    @Length(min = 10, max = 100, message = "A senha deve conter entre 10 e 100 caracteres")
    @Schema(example = "SenhaSegura@0123", minLength = 10, maxLength = 100, requiredMode = RequiredMode.REQUIRED, description = "Senha do candidato")
    private String password;

    @Schema(example = "Vaga desenvolvedor Java")
    private String description;

    private String curriculum;
}
