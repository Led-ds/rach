package com.br.rach.domain.dtos.person;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class PersonUpdateRequestDTO {
    private UUID id;
    private String name;
    private String color;
}
