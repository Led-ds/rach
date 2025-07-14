package com.br.rach.domain.services.person;

import com.br.rach.domain.dtos.person.PersonCreateRequestDTO;
import com.br.rach.domain.dtos.person.PersonDTO;
import com.br.rach.domain.dtos.person.PersonUpdateRequestDTO;
import com.br.rach.domain.entities.Person;
import com.br.rach.domain.mappers.PersonMapper;
import com.br.rach.infrastructure.repositories.PersonRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;
    private final PersonMapper personMapper;

    @Override
    public List<PersonDTO> findAll() {
        log.debug("Finding all persons");
        List<Person> persons = personRepository.findAll();
        return personMapper.toDto(persons);
    }

    @Override
    public Optional<PersonDTO> findById(UUID id) {
        log.debug("Finding person by id: {}", id);
        return personRepository.findById(id)
                .map(personMapper::toDto);
    }

    @Override
    @Transactional
    public PersonDTO create(PersonCreateRequestDTO createDto) {
        log.debug("Creating person with name: {}", createDto.getName());

        validateCreateDto(createDto);

        Person person = personMapper.toEntity(createDto);
        Person savedPerson = personRepository.save(person);

        log.info("Person created successfully with id: {}", savedPerson.getId());
        return personMapper.toDto(savedPerson);
    }

    @Override
    @Transactional
    public Optional<PersonDTO> update(UUID id, PersonUpdateRequestDTO updateDto) {
        log.debug("Updating person with id: {}", id);

        return personRepository.findById(id)
                .map(person -> {
                    validateUpdateDto(updateDto);
                    updatePersonFromDto(person, updateDto);
                    Person savedPerson = personRepository.save(person);
                    log.info("Person updated successfully with id: {}", savedPerson.getId());
                    return personMapper.toDto(savedPerson);
                });
    }

    @Override
    @Transactional
    public boolean delete(UUID id) {
        log.debug("Deleting person with id: {}", id);

        if (personRepository.existsById(id)) {
            personRepository.deleteById(id);
            log.info("Person deleted successfully with id: {}", id);
            return true;
        }

        log.warn("Person not found for deletion with id: {}", id);
        return false;
    }

    @Override
    public boolean existsById(UUID id) {
        return personRepository.existsById(id);
    }

    @Override
    public long count() {
        return personRepository.count();
    }

    private void validateCreateDto(PersonCreateRequestDTO createDto) {
        if (createDto.getName() == null || createDto.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Person name cannot be null or empty");
        }

        if (createDto.getColor() == null || !isValidColor(createDto.getColor())) {
            throw new IllegalArgumentException("Person color must be a valid hex color");
        }
    }

    private void validateUpdateDto(PersonUpdateRequestDTO updateDto) {
        if (updateDto.getName() != null && updateDto.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Person name cannot be empty");
        }

        if (updateDto.getColor() != null && !isValidColor(updateDto.getColor())) {
            throw new IllegalArgumentException("Person color must be a valid hex color");
        }
    }

    private void updatePersonFromDto(Person person, PersonUpdateRequestDTO updateDto) {
        if (updateDto.getName() != null) {
            person.updateName(updateDto.getName());
        }

        if (updateDto.getColor() != null) {
            person.updateColor(updateDto.getColor());
        }
    }

    private boolean isValidColor(String color) {
        return color.matches("^#([A-Fa-f0-9]{6}|[A-Fa-f0-9]{3})$");
    }
}