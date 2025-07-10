package com.br.rach.domain.services.person;

import com.br.rach.domain.dtos.person.PersonCreateRequestDTO;
import com.br.rach.domain.dtos.person.PersonDTO;
import com.br.rach.domain.dtos.person.PersonUpdateRequestDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PersonService {
    List<PersonDTO> findAll();

    Optional<PersonDTO> findById(UUID id);

    PersonDTO create(PersonCreateRequestDTO createDto);

    Optional<PersonDTO> update(UUID id, PersonUpdateRequestDTO updateDto);

    boolean delete(UUID id);

    boolean existsById(UUID id);

    long count();
}
