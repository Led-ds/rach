package com.br.rach.domain.dtos.person;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PersonCreateRequestDTO {
    private String name;
    private String color;
}
