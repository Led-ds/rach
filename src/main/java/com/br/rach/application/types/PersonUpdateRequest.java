package com.br.rach.application.types;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Schema(description = "Request para atualização de pessoa")
public class PersonUpdateRequest {

    @Size(min = 2, max = 255, message = "Nome deve ter entre 2 e 255 caracteres")
    @Schema(description = "Nome da pessoa", example = "João Silva")
    private String name;

    @Pattern(regexp = "^#([A-Fa-f0-9]{6}|[A-Fa-f0-9]{3})$", message = "Cor deve ser um código hexadecimal válido")
    @Schema(description = "Cor em formato hexadecimal", example = "#FF5733")
    private String color;
}