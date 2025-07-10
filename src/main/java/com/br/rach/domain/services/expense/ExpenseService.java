package com.br.rach.domain.services.expense;

import com.br.rach.domain.dtos.expense.ExpenseCreateRequestDTO;
import com.br.rach.domain.dtos.expense.ExpenseDTO;
import com.br.rach.domain.dtos.expense.ExpenseUpdateRequestDTO;

import java.util.List;
import java.util.UUID;

public interface ExpenseService {
    // Buscar todas as despesas
    List<ExpenseDTO> getAllExpenses();

    // Buscar despesa por ID
    ExpenseDTO getExpenseById(UUID id);

    // Criar uma nova despesa
    void createExpense(ExpenseCreateRequestDTO expenseCreateRequestDTO);

    // Atualizar uma despesa existente
    void updateExpense(UUID id, ExpenseUpdateRequestDTO expenseUpdateRequestDTO);

    // Deletar uma despesa
    void deleteExpense(UUID id);
}
