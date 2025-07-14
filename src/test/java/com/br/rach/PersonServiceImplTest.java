package com.br.rach;

import com.br.rach.domain.dtos.person.PersonCreateRequestDTO;
import com.br.rach.domain.dtos.person.PersonDTO;
import com.br.rach.domain.dtos.person.PersonUpdateRequestDTO;
import com.br.rach.domain.entities.Person;
import com.br.rach.domain.mappers.PersonMapper;
import com.br.rach.domain.services.person.PersonServiceImpl;
import com.br.rach.infrastructure.repositories.PersonRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PersonServiceImplTest {

    @Mock
    private PersonRepository personRepository;

    @Mock
    private PersonMapper personMapper;

    @InjectMocks
    private PersonServiceImpl personService;

    private Person person;
    private PersonDTO personDto;
    private PersonCreateRequestDTO personCreateDto;
    private PersonUpdateRequestDTO personUpdateDto;

    @BeforeEach
    void setUp() {
        UUID id = UUID.randomUUID();

        person = Person.builder()
                .id(id)
                .name("João Silva")
                .color("#FF5733")
                .build();

        personDto = PersonDTO.builder()
                .id(id)
                .name("João Silva")
                .color("#FF5733")
                .build();

        personCreateDto = PersonCreateRequestDTO.builder()
                .name("João Silva")
                .color("#FF5733")
                .build();

        personUpdateDto = PersonUpdateRequestDTO.builder()
                .id(id)
                .name("João Silva Updated")
                .color("#00FF00")
                .build();
    }

    @Test
    @DisplayName("Should find all persons successfully")
    void shouldFindAllPersonsSuccessfully() {
        // Given
        List<Person> persons = Arrays.asList(person);
        List<PersonDTO> personDtos = Arrays.asList(personDto);

        when(personRepository.findAll()).thenReturn(persons);
        when(personMapper.toDto(persons)).thenReturn(personDtos);

        // When
        List<PersonDTO> result = personService.findAll();

        // Then
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(personDto.getName(), result.get(0).getName());

        verify(personRepository).findAll();
        verify(personMapper).toDto(persons);
    }

    @Test
    @DisplayName("Should find person by id successfully")
    void shouldFindPersonByIdSuccessfully() {
        // Given
        UUID id = person.getId();
        when(personRepository.findById(id)).thenReturn(Optional.of(person));
        when(personMapper.toDto(person)).thenReturn(personDto);

        // When
        Optional<PersonDTO> result = personService.findById(id);

        // Then
        assertTrue(result.isPresent());
        assertEquals(personDto.getName(), result.get().getName());

        verify(personRepository).findById(id);
        verify(personMapper).toDto(person);
    }

    @Test
    @DisplayName("Should return empty when person not found by id")
    void shouldReturnEmptyWhenPersonNotFoundById() {
        // Given
        UUID id = UUID.randomUUID();
        when(personRepository.findById(id)).thenReturn(Optional.empty());

        // When
        Optional<PersonDTO> result = personService.findById(id);

        // Then
        assertFalse(result.isPresent());

        verify(personRepository).findById(id);
        verify(personMapper, never()).toDto(any(Person.class));
    }

    @Test
    @DisplayName("Should create person successfully")
    void shouldCreatePersonSuccessfully() {
        // Given
        when(personMapper.toEntity(personCreateDto)).thenReturn(person);
        when(personRepository.save(person)).thenReturn(person);
        when(personMapper.toDto(person)).thenReturn(personDto);

        // When
        PersonDTO result = personService.create(personCreateDto);

        // Then
        assertNotNull(result);
        assertEquals(personDto.getName(), result.getName());

        verify(personMapper).toEntity(personCreateDto);
        verify(personRepository).save(person);
        verify(personMapper).toDto(person);
    }

    @Test
    @DisplayName("Should throw exception when creating person with null name")
    void shouldThrowExceptionWhenCreatingPersonWithNullName() {
        // Given
        PersonCreateRequestDTO invalidDto = PersonCreateRequestDTO.builder()
                .name(null)
                .color("#FF5733")
                .build();

        // When & Then
        assertThrows(IllegalArgumentException.class, () -> personService.create(invalidDto));

        verify(personRepository, never()).save(any());
    }

    @Test
    @DisplayName("Should update person successfully")
    void shouldUpdatePersonSuccessfully() {
        // Given
        UUID id = person.getId();
        when(personRepository.findById(id)).thenReturn(Optional.of(person));
        when(personRepository.save(person)).thenReturn(person);
        when(personMapper.toDto(person)).thenReturn(personDto);

        // When
        Optional<PersonDTO> result = personService.update(id, personUpdateDto);

        // Then
        assertTrue(result.isPresent());
        assertEquals(personDto.getName(), result.get().getName());

        verify(personRepository).findById(id);
        verify(personRepository).save(person);
        verify(personMapper).toDto(person);
    }

    @Test
    @DisplayName("Should delete person successfully")
    void shouldDeletePersonSuccessfully() {
        // Given
        UUID id = person.getId();
        when(personRepository.existsById(id)).thenReturn(true);

        // When
        boolean result = personService.delete(id);

        // Then
        assertTrue(result);

        verify(personRepository).existsById(id);
        verify(personRepository).deleteById(id);
    }

    @Test
    @DisplayName("Should return false when deleting non-existent person")
    void shouldReturnFalseWhenDeletingNonExistentPerson() {
        // Given
        UUID id = UUID.randomUUID();
        when(personRepository.existsById(id)).thenReturn(false);

        // When
        boolean result = personService.delete(id);

        // Then
        assertFalse(result);

        verify(personRepository).existsById(id);
        verify(personRepository, never()).deleteById(any());
    }
}