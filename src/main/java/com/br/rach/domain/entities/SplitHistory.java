package com.br.rach.domain.entities;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "split_history")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(of = "id")
public class SplitHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(columnDefinition = "uuid", updatable = false, nullable = false)
    private UUID id;

    private String name;

    private LocalDate date;

    @ManyToMany
    @JoinTable(
            name = "split_history_people",
            joinColumns = @JoinColumn(name = "split_history_id"),
            inverseJoinColumns = @JoinColumn(name = "person_id"))
    private List<Person> people;  // Pessoas associadas ao histórico do split

    @ManyToMany
    @JoinTable(
            name = "split_history_expenses",
            joinColumns = @JoinColumn(name = "split_history_id"),
            inverseJoinColumns = @JoinColumn(name = "expense_id"))
    private List<Expense> expenses;  // Despesas associadas ao histórico do split

    private BigDecimal totalAmount;

    @Enumerated(EnumType.STRING)
    private SplitStatus status;  // Status do split (pendente ou concluído)

    private String template;  // Template utilizado, se houver

    public enum SplitStatus {
        PENDING, COMPLETED
    }
}