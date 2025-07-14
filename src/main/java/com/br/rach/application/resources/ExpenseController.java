package com.br.rach.application.resources;

import com.br.rach.domain.dtos.expense.ExpenseCreateRequestDTO;
import com.br.rach.domain.dtos.expense.ExpenseDTO;
import com.br.rach.domain.dtos.expense.ExpenseUpdateRequestDTO;
import com.br.rach.domain.services.expense.ExpenseService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/expenses")
public class ExpenseController {

    private final ExpenseService expenseService;
    private final ExpenseControllerMapper expenseControllerMapper;

    public ExpenseController(ExpenseService expenseService, ExpenseControllerMapper expenseControllerMapper) {
        this.expenseService = expenseService;
        this.expenseControllerMapper = expenseControllerMapper;
    }

    @GetMapping
    public List<ExpenseDTO> getAllExpenses() {
        return expenseService.getAllExpenses();
    }

    @GetMapping("/{id}")
    public ExpenseDTO getExpenseById(@PathVariable UUID id) {
        return expenseService.getExpenseById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createExpense(@RequestBody ExpenseCreateRequestDTO expenseCreateRequestDTO) {
        expenseService.createExpense(expenseCreateRequestDTO);
    }

    @PutMapping("/{id}")
    public void updateExpense(@PathVariable UUID id, @RequestBody ExpenseUpdateRequestDTO expenseUpdateRequestDTO) {
        expenseService.updateExpense(id, expenseUpdateRequestDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteExpense(@PathVariable UUID id) {
        expenseService.deleteExpense(id);
    }
}