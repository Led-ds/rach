package com.br.rach.domain.mappers;

import com.br.rach.domain.dtos.person.PersonCreateRequestDTO;
import com.br.rach.domain.dtos.person.PersonDTO;
import com.br.rach.domain.dtos.person.PersonResponse;
import com.br.rach.domain.entities.Person;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface PersonMapper {

    PersonDTO toDto(Person person);

    List<PersonDTO> toDto(List<Person> persons);

    PersonResponse toResponse(PersonDTO personDto);

    List<PersonResponse> toResponse(List<PersonDTO> personDtos);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "version", ignore = true)
    Person toEntity(PersonCreateRequestDTO createDto);

    void updateEntity(@MappingTarget Person person, PersonCreateRequestDTO createDto);
}
