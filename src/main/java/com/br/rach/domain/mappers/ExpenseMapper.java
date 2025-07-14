package com.br.rach.domain.mappers;

import com.br.rach.domain.dtos.expense.ExpenseCreateRequestDTO;
import com.br.rach.domain.dtos.expense.ExpenseDTO;
import com.br.rach.domain.entities.Expense;

public class ExpenseMapper {

    public static ExpenseDTO toDTO(Expense expense) {
        return ExpenseDTO.builder()
                .id(expense.getId())
                .description(expense.getDescription())
                .amount(expense.getAmount())
                .paidBy(expense.getPaidBy())
                .category(expense.getCategory())
                .date(expense.getDate().toString())  // Convertendo LocalDate para String
                .splitBetween(expense.getSplitBetween())
                .splitType(expense.getSplitType())
                .splitData(expense.getSplitData())
                .build();
    }

    public static Expense toEntity(ExpenseCreateRequestDTO expenseCreateRequestDTO) {
        return Expense.builder()
                .description(expenseCreateRequestDTO.getDescription())
                .amount(expenseCreateRequestDTO.getAmount())
                .paidBy(expenseCreateRequestDTO.getPaidBy())
                .category(expenseCreateRequestDTO.getCategory())
                .date(java.time.LocalDate.parse(expenseCreateRequestDTO.getDate()))  // Convertendo String para LocalDate
                .build();
    }

}
