package com.br.rach.infrastructure.repositories;

import com.br.rach.domain.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PersonRepository extends JpaRepository<Person, UUID> {

    Optional<Person> findByNameIgnoreCase(String name);

    @Query("SELECT p FROM Person p WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<Person> findByNameContainingIgnoreCase(@Param("name") String name);

    @Query("SELECT COUNT(p) > 0 FROM Person p WHERE LOWER(p.name) = LOWER(:name) AND p.id != :id")
    boolean existsByNameIgnoreCaseAndIdNot(@Param("name") String name, @Param("id") UUID id);

    @Query("SELECT COUNT(p) > 0 FROM Person p WHERE LOWER(p.name) = LOWER(:name)")
    boolean existsByNameIgnoreCase(@Param("name") String name);

    List<Person> findByColorIgnoreCase(String color);

    @Query("SELECT p FROM Person p ORDER BY p.name ASC")
    List<Person> findAllOrderByName();

    @Query("SELECT p FROM Person p ORDER BY p.createdAt DESC")
    List<Person> findAllOrderByCreatedAtDesc();
}
