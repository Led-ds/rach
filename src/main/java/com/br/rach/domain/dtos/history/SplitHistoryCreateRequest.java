package com.br.rach.domain.dtos.history;

import com.br.rach.domain.entities.Expense;
import com.br.rach.domain.entities.Person;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class SplitHistoryCreateRequest {
    private String name;
    private List<Person> people;
    private List<Expense> expenses;
    private String template;
}
