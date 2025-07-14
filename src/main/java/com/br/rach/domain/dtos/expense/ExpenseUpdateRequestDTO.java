package com.br.rach.domain.dtos.expense;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;


@Data
@Builder
public class ExpenseUpdateRequestDTO {
    private String description;
    private BigDecimal amount;
    private String paidBy;
    private String category;
    private String date;
}
