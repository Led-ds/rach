package com.br.rach.domain.entities;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Split {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(columnDefinition = "uuid", updatable = false, nullable = false)
    private UUID id;

    private String name;

    private LocalDate date;

    @ManyToMany
    @JoinTable(
            name = "split_people",
            joinColumns = @JoinColumn(name = "split_id"),
            inverseJoinColumns = @JoinColumn(name = "person_id"))
    private List<Person> people;  // Pessoas associadas ao split

    @ManyToMany
    @JoinTable(
            name = "split_expenses",
            joinColumns = @JoinColumn(name = "split_id"),
            inverseJoinColumns = @JoinColumn(name = "expense_id"))
    private List<Expense> expenses;  // Despesas associadas ao split

    private BigDecimal totalAmount;

    @Enumerated(EnumType.STRING)
    private SplitStatus status;  // Status do split (pendente ou concluído)

    private String template;  // Template opcional para o split

    public enum SplitStatus {
        PENDING, COMPLETED
    }
}
