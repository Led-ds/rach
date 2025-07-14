package com.br.rach.application.types;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
@Schema(description = "Modelo de pessoa")
public class PersonModel {

    @Schema(description = "Identificador único da pessoa", example = "123e4567-e89b-12d3-a456-426614174000")
    private UUID id;

    @Schema(description = "Nome da pessoa", example = "João Silva")
    private String name;

    @Schema(description = "Cor para identificação visual", example = "#FF5733")
    private String color;
}