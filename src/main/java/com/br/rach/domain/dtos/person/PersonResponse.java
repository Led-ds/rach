package com.br.rach.domain.dtos.person;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class PersonResponse {
    private UUID id;
    private String name;
    private String color;
}