package com.br.rach.domain.dtos.expense;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Data
@Builder
public class ExpenseDTO {
    private UUID id;
    private String description;
    private BigDecimal amount;
    private String paidBy;
    private String category;
    private String date;
    private List<String> splitBetween;
    private String splitType;
    private Map<String, BigDecimal> splitData;
}
