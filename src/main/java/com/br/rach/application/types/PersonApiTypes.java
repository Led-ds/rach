package com.br.rach.application.types;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.UUID;

@Tag(name = "Person", description = "API para gerenciamento de pessoas")
public interface PersonApiTypes {

    @Operation(
            summary = "Buscar todas as pessoas",
            description = "Retorna uma lista com todas as pessoas cadastradas no sistema"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Lista de pessoas retornada com sucesso",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = PersonModel.class)
                    )
            )
    })
    ResponseEntity<List<PersonModel>> getAllPersons();

    @Operation(
            summary = "Buscar pessoa por ID",
            description = "Retorna uma pessoa específica baseada no ID fornecido"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Pessoa encontrada com sucesso",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = PersonModel.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Pessoa não encontrada",
                    content = @Content
            )
    })
    ResponseEntity<PersonModel> getPersonById(
            @Parameter(description = "ID da pessoa", required = true)
            @PathVariable UUID id
    );

    @Operation(
            summary = "Criar nova pessoa",
            description = "Cria uma nova pessoa no sistema"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Pessoa criada com sucesso",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = PersonModel.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Dados inválidos fornecidos",
                    content = @Content
            )
    })
    ResponseEntity<PersonModel> createPerson(
            @Parameter(description = "Dados para criação da pessoa", required = true)
            @RequestBody PersonCreateRequest request
    );

    @Operation(
            summary = "Atualizar pessoa existente",
            description = "Atualiza os dados de uma pessoa existente"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Pessoa atualizada com sucesso",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = PersonModel.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Pessoa não encontrada",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Dados inválidos fornecidos",
                    content = @Content
            )
    })
    ResponseEntity<PersonModel> updatePerson(
            @Parameter(description = "ID da pessoa", required = true)
            @PathVariable UUID id,
            @Parameter(description = "Dados para atualização da pessoa", required = true)
            @RequestBody PersonUpdateRequest request
    );

    @Operation(
            summary = "Deletar pessoa",
            description = "Remove uma pessoa do sistema"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "204",
                    description = "Pessoa deletada com sucesso"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Pessoa não encontrada",
                    content = @Content
            )
    })
    ResponseEntity<Void> deletePerson(
            @Parameter(description = "ID da pessoa", required = true)
            @PathVariable UUID id
    );
}