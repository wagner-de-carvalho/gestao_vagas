package br.com.acme.gestao_vagas.modules.company.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateJobDTO {
    @Schema(example = "Vaga para desenvolvedor(a) júnior", requiredMode = RequiredMode.REQUIRED)
    private String description;

    @Schema(example = "Plano de saúde, GYMPass", requiredMode = RequiredMode.REQUIRED)
    private String benefits;

    @Schema(example = "PLENO", requiredMode = RequiredMode.REQUIRED)
    private String level;
}
