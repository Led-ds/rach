package com.br.rach.domain.entities;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Entity
@Table(name = "expenses")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(of = "id")
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(columnDefinition = "uuid", updatable = false, nullable = false)
    private UUID id;

    private String description;

    private BigDecimal amount;

    private String paidBy;

    private String category;

    private LocalDate date;

    @ElementCollection
    private List<String> splitBetween;

    private String splitType;

    @ElementCollection
    @MapKeyColumn(name = "person_name")
    @Column(name = "amount")
    private Map<String, BigDecimal> splitData;

}