package com.br.rach;

import com.br.rach.domain.entities.Person;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest {

    @Test
    @DisplayName("Should create person with valid data")
    void shouldCreatePersonWithValidData() {
        // Given
        String name = "João Silva";
        String color = "#FF5733";

        // When
        Person person = Person.builder()
                .name(name)
                .color(color)
                .build();

        // Then
        assertEquals(name, person.getName());
        assertEquals(color, person.getColor());
        assertTrue(person.isActive());
    }

    @Test
    @DisplayName("Should update name successfully")
    void shouldUpdateNameSuccessfully() {
        // Given
        Person person = Person.builder()
                .name("João Silva")
                .color("#FF5733")
                .build();
        String newName = "Maria Santos";

        // When
        person.updateName(newName);

        // Then
        assertEquals(newName, person.getName());
    }

    @Test
    @DisplayName("Should throw exception when updating with null name")
    void shouldThrowExceptionWhenUpdatingWithNullName() {
        // Given
        Person person = Person.builder()
                .name("João Silva")
                .color("#FF5733")
                .build();

        // When & Then
        assertThrows(IllegalArgumentException.class, () -> person.updateName(null));
    }

    @Test
    @DisplayName("Should throw exception when updating with empty name")
    void shouldThrowExceptionWhenUpdatingWithEmptyName() {
        // Given
        Person person = Person.builder()
                .name("João Silva")
                .color("#FF5733")
                .build();

        // When & Then
        assertThrows(IllegalArgumentException.class, () -> person.updateName(""));
    }

    @Test
    @DisplayName("Should update color successfully")
    void shouldUpdateColorSuccessfully() {
        // Given
        Person person = Person.builder()
                .name("João Silva")
                .color("#FF5733")
                .build();
        String newColor = "#00FF00";

        // When
        person.updateColor(newColor);

        // Then
        assertEquals(newColor, person.getColor());
    }

    @Test
    @DisplayName("Should throw exception when updating with invalid color")
    void shouldThrowExceptionWhenUpdatingWithInvalidColor() {
        // Given
        Person person = Person.builder()
                .name("João Silva")
                .color("#FF5733")
                .build();

        // When & Then
        assertThrows(IllegalArgumentException.class, () -> person.updateColor("invalid-color"));
    }
}
