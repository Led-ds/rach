package com.br.rach.domain.dtos.history;

import com.br.rach.domain.entities.Expense;
import com.br.rach.domain.entities.Person;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Data
@Builder
public class SplitHistoryDTO {
    private UUID id;
    private String name;
    private String date;
    private List<Person> people;
    private List<Expense> expenses;
    private BigDecimal totalAmount;
    private String status;
    private String template;
}
