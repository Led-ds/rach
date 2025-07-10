package com.br.rach.application.resources;

import com.br.rach.application.types.PersonCreateRequest;
import com.br.rach.application.types.PersonModel;
import com.br.rach.application.types.PersonUpdateRequest;
import com.br.rach.domain.dtos.person.PersonCreateRequestDTO;
import com.br.rach.domain.dtos.person.PersonDTO;
import com.br.rach.domain.dtos.person.PersonUpdateRequestDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.UUID;

@Mapper(componentModel = "spring")
public interface PersonControllerMapper {

    PersonModel toModel(PersonDTO personDto);

    List<PersonModel> toModel(List<PersonDTO> personDtos);

    PersonCreateRequestDTO toCreateDto(PersonCreateRequest request);

    @Mapping(target = "id", source = "id")
    PersonUpdateRequestDTO toUpdateDto(UUID id, PersonUpdateRequest request);
}