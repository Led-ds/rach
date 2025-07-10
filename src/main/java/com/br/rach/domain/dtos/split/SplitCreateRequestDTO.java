package com.br.rach.domain.dtos.split;

import com.br.rach.domain.dtos.person.PersonDTO;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class SplitCreateRequestDTO {
    private String name;
    private List<PersonDTO> people;
    private String template;
}
