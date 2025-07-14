package com.br.rach.domain.dtos.template;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class SplitTemplateDTO {
    private UUID id;
    private String name;
    private String description;
    private String icon;
    private List<DefaultExpense> defaultExpenses;

    @Data
    public static class DefaultExpense {
        private String description;
        private String category;
    }
}
