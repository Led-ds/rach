package com.br.rach.application.resources;

import com.br.rach.application.types.*;
import com.br.rach.domain.dtos.person.PersonCreateRequestDTO;
import com.br.rach.domain.dtos.person.PersonDTO;
import com.br.rach.domain.dtos.person.PersonUpdateRequestDTO;
import com.br.rach.domain.mappers.PersonMapper;
import com.br.rach.domain.services.person.PersonService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/people")
@RequiredArgsConstructor
@Slf4j
public class PersonController implements PersonApiTypes {

    private final PersonService personService;
    private final PersonControllerMapper controllerMapper;

    @Override
    @GetMapping
    public ResponseEntity<List<PersonModel>> getAllPersons() {
        log.debug("GET /people - Finding all persons");

        List<PersonDTO> personDtos = personService.findAll();
        List<PersonModel> personModels = controllerMapper.toModel(personDtos);

        log.debug("Found {} persons", personModels.size());
        return ResponseEntity.ok(personModels);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<PersonModel> getPersonById(@PathVariable UUID id) {
        log.debug("GET /people/{} - Finding person by id", id);

        return personService.findById(id)
                .map(personDto -> {
                    PersonModel personModel = controllerMapper.toModel(personDto);
                    log.debug("Person found: {}", personModel.getName());
                    return ResponseEntity.ok(personModel);
                })
                .orElseGet(() -> {
                    log.debug("Person not found with id: {}", id);
                    return ResponseEntity.notFound().build();
                });
    }

    @Override
    @PostMapping
    public ResponseEntity<PersonModel> createPerson(@Valid @RequestBody PersonCreateRequest request) {
        log.debug("POST /people - Creating person with name: {}", request.getName());

        PersonCreateRequestDTO createDto = controllerMapper.toCreateDto(request);
        PersonDTO createdPerson = personService.create(createDto);
        PersonModel personModel = controllerMapper.toModel(createdPerson);

        log.info("Person created successfully with id: {}", personModel.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(personModel);
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<PersonModel> updatePerson(@PathVariable UUID id, @Valid @RequestBody PersonUpdateRequest request) {
        log.debug("PUT /people/{} - Updating person", id);

        PersonUpdateRequestDTO updateDto = controllerMapper.toUpdateDto(id, request);

        return personService.update(id, updateDto)
                .map(personDto -> {
                    PersonModel personModel = controllerMapper.toModel(personDto);
                    log.info("Person updated successfully with id: {}", personModel.getId());
                    return ResponseEntity.ok(personModel);
                })
                .orElseGet(() -> {
                    log.debug("Person not found for update with id: {}", id);
                    return ResponseEntity.notFound().build();
                });
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePerson(@PathVariable UUID id) {
        log.debug("DELETE /people/{} - Deleting person", id);

        boolean deleted = personService.delete(id);

        if (deleted) {
            log.info("Person deleted successfully with id: {}", id);
            return ResponseEntity.noContent().build();
        } else {
            log.debug("Person not found for deletion with id: {}", id);
            return ResponseEntity.notFound().build();
        }
    }
}
