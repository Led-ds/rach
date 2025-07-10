package com.br.rach.domain.services.expense;

import com.br.rach.application.resources.ExpenseControllerMapper;
import com.br.rach.domain.dtos.expense.ExpenseCreateRequestDTO;
import com.br.rach.domain.dtos.expense.ExpenseDTO;
import com.br.rach.domain.dtos.expense.ExpenseUpdateRequestDTO;
import com.br.rach.domain.entities.Expense;
import com.br.rach.infrastructure.repositories.ExpenseRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ExpenseServiceImpl implements ExpenseService {

    private final ExpenseRepository expenseRepository;
    private final ExpenseControllerMapper expenseControllerMapper;


    public ExpenseServiceImpl(ExpenseRepository expenseRepository, ExpenseControllerMapper expenseControllerMapper) {
        this.expenseRepository = expenseRepository;
        this.expenseControllerMapper = expenseControllerMapper;
    }

    @Override
    public List<ExpenseDTO> getAllExpenses() {
        return expenseRepository.findAll()
                .stream()
                .map(expenseControllerMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ExpenseDTO getExpenseById(UUID id) {
        return expenseRepository.findById(id)
                .map(expenseControllerMapper::toDTO)
                .orElseThrow(() -> new RuntimeException("Expense not found"));
    }

    @Override
    public void createExpense(ExpenseCreateRequestDTO expenseCreateRequestDTO) {
        Expense expense = expenseControllerMapper.toEntity(expenseCreateRequestDTO);
        expenseRepository.save(expense);
    }

    @Override
    public void updateExpense(UUID id, ExpenseUpdateRequestDTO expenseUpdateRequestDTO) {
        Expense expense = expenseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Expense not found"));
        expense = expenseControllerMapper.toEntity(id, expenseUpdateRequestDTO);
        expenseRepository.save(expense);
    }

    @Override
    public void deleteExpense(UUID id) {
        expenseRepository.deleteById(id);
    }

    public List<ExpenseDTO> getExpensesByCategory(String category) {
        return expenseRepository.findByCategoryIgnoreCase(category)
                .stream()
                .map(expenseControllerMapper::toDTO)
                .collect(Collectors.toList());
    }

    public List<ExpenseDTO> getExpensesByAmountGreaterThan(BigDecimal amount) {
        return expenseRepository.findByAmountGreaterThan(amount)
                .stream()
                .map(expenseControllerMapper::toDTO)
                .collect(Collectors.toList());
    }
}