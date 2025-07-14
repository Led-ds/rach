package com.br.rach;

import com.br.rach.application.types.PersonCreateRequest;
import com.br.rach.application.types.PersonUpdateRequest;
import com.br.rach.domain.entities.Person;
import com.br.rach.infrastructure.repositories.PersonRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.UUID;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@Testcontainers
@ActiveProfiles("test")
@Transactional
@AutoConfigureWebMvc
class PersonIntegrationTest {

    @Container
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:15-alpine")
            .withDatabaseName("rach_test")
            .withUsername("test")
            .withPassword("test");

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private ObjectMapper objectMapper;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        personRepository.deleteAll();
    }

    @Test
    @DisplayName("Should create person successfully")
    void shouldCreatePersonSuccessfully() throws Exception {
        // Given
        PersonCreateRequest request = PersonCreateRequest.builder()
                .name("João Silva")
                .color("#FF5733")
                .build();

        // When & Then
        mockMvc.perform(post("/api/v1/people")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", is("João Silva")))
                .andExpect(jsonPath("$.color", is("#FF5733")))
                .andExpect(jsonPath("$.id", notNullValue()));
    }

    @Test
    @DisplayName("Should return validation error when creating person with invalid data")
    void shouldReturnValidationErrorWhenCreatingPersonWithInvalidData() throws Exception {
        // Given
        PersonCreateRequest request = PersonCreateRequest.builder()
                .name("") // Nome vazio
                .color("invalid-color") // Cor inválida
                .build();

        // When & Then
        mockMvc.perform(post("/api/v1/people")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status", is(400)))
                .andExpect(jsonPath("$.error", is("Validation Failed")))
                .andExpect(jsonPath("$.validationErrors", notNullValue()));
    }

    @Test
    @DisplayName("Should get all persons successfully")
    void shouldGetAllPersonsSuccessfully() throws Exception {
        // Given
        Person person1 = createAndSavePerson("João Silva", "#FF5733");
        Person person2 = createAndSavePerson("Maria Santos", "#00FF00");

        // When & Then
        mockMvc.perform(get("/api/v1/people"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].name", is("João Silva")))
                .andExpect(jsonPath("$[1].name", is("Maria Santos")));
    }

    @Test
    @DisplayName("Should get person by id successfully")
    void shouldGetPersonByIdSuccessfully() throws Exception {
        // Given
        Person person = createAndSavePerson("João Silva", "#FF5733");

        // When & Then
        mockMvc.perform(get("/api/v1/people/{id}", person.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(person.getId().toString())))
                .andExpect(jsonPath("$.name", is("João Silva")))
                .andExpect(jsonPath("$.color", is("#FF5733")));
    }

    @Test
    @DisplayName("Should return 404 when person not found")
    void shouldReturn404WhenPersonNotFound() throws Exception {
        // Given
        UUID nonExistentId = UUID.randomUUID();

        // When & Then
        mockMvc.perform(get("/api/v1/people/{id}", nonExistentId))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("Should update person successfully")
    void shouldUpdatePersonSuccessfully() throws Exception {
        // Given
        Person person = createAndSavePerson("João Silva", "#FF5733");

        PersonUpdateRequest request = PersonUpdateRequest.builder()
                .name("João Silva Updated")
                .color("#00FF00")
                .build();

        // When & Then
        mockMvc.perform(put("/api/v1/people/{id}", person.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(person.getId().toString())))
                .andExpect(jsonPath("$.name", is("João Silva Updated")))
                .andExpect(jsonPath("$.color", is("#00FF00")));
    }

    @Test
    @DisplayName("Should delete person successfully")
    void shouldDeletePersonSuccessfully() throws Exception {
        // Given
        Person person = createAndSavePerson("João Silva", "#FF5733");

        // When & Then
        mockMvc.perform(delete("/api/v1/people/{id}", person.getId()))
                .andExpect(status().isNoContent());

        // Verify person was deleted
        mockMvc.perform(get("/api/v1/people/{id}", person.getId()))
                .andExpect(status().isNotFound());
    }

    private Person createAndSavePerson(String name, String color) {
        Person person = Person.builder()
                .name(name)
                .color(color)
                .build();
        return personRepository.save(person);
    }
}
