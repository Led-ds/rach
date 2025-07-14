package com.br.rach.domain.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "default_expense")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(of = "id")
public class DefaultExpense {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(columnDefinition = "uuid", updatable = false, nullable = false)
    private UUID id;

    private String description;

    private String category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "template_id")  // Relacionando com o SplitTemplate
    private SplitTemplate splitTemplate;  // Relacionamento com SplitTemplate

}