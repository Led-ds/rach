package com.br.rach.domain.dtos.split;

import com.br.rach.domain.dtos.expense.ExpenseDTO;
import com.br.rach.domain.dtos.person.PersonDTO;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Data
@Builder
public class SplitDTO {
    private UUID id;
    private String name;
    private String date;
    private List<PersonDTO> people;
    private List<ExpenseDTO> expenses;
    private BigDecimal totalAmount;
    private String status;
    private String template;
}
