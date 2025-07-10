package com.br.rach.application.resources;

import com.br.rach.domain.dtos.expense.ExpenseCreateRequestDTO;
import com.br.rach.domain.dtos.expense.ExpenseDTO;
import com.br.rach.domain.dtos.expense.ExpenseUpdateRequestDTO;
import com.br.rach.domain.entities.Expense;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.UUID;

@Mapper(componentModel = "spring")
public interface ExpenseControllerMapper {

    // Mapeamento de ExpenseDTO para ExpenseModel (utilizado pelo front-end)
    Expense toModel(ExpenseDTO expenseDto);

    // Mapeamento de uma lista de ExpenseDTO para uma lista de ExpenseModel
    List<Expense> toModel(List<ExpenseDTO> expenseDtos);

    // Mapeamento de ExpenseCreateRequestDTO para Expense (entidade)
    Expense toEntity(ExpenseCreateRequestDTO expenseCreateRequestDTO);

    // Mapeamento de ExpenseUpdateRequestDTO para Expense (entidade) com a id
    @Mapping(target = "id", source = "id")
    Expense toEntity(UUID id, ExpenseUpdateRequestDTO expenseUpdateRequestDTO);

    // Mapeamento de Expense para ExpenseDTO (usado para retornar dados da entidade para o front-end)
    ExpenseDTO toDTO(Expense expense);

    // Mapeamento de Expense para ExpenseCreateRequestDTO (utilizado para capturar dados de criação)
    ExpenseCreateRequestDTO toCreateDto(Expense expense);
}
