package com.br.rach.infrastructure.repositories;

import com.br.rach.domain.entities.Split;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface SplitRepository extends JpaRepository<Split, UUID> {
    // Encontrar um Split pelo nome (ignorando maiúsculas/minúsculas)
    Optional<Split> findByNameIgnoreCase(String name);

    // Buscar splits com o nome contendo o termo fornecido (case insensitive)
    @Query("SELECT s FROM Split s WHERE LOWER(s.name) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<Split> findByNameContainingIgnoreCase(@Param("name") String name);

    // Verificar se já existe um Split com o nome fornecido (excluindo o Split com o ID fornecido)
    @Query("SELECT COUNT(s) > 0 FROM Split s WHERE LOWER(s.name) = LOWER(:name) AND s.id != :id")
    boolean existsByNameIgnoreCaseAndIdNot(@Param("name") String name, @Param("id") Long id);

    // Verificar se já existe um Split com o nome fornecido
    @Query("SELECT COUNT(s) > 0 FROM Split s WHERE LOWER(s.name) = LOWER(:name)")
    boolean existsByNameIgnoreCase(@Param("name") String name);

    // Buscar todos os Splits com base no status (PENDING ou COMPLETED)
    List<Split> findByStatus(String status);

    // Buscar todos os Splits com base na data
    List<Split> findByDate(LocalDate date);

    // Buscar todos os Splits que ocorreram em um intervalo de datas
    List<Split> findByDateBetween(LocalDate startDate, LocalDate endDate);

    // Buscar todos os Splits de uma pessoa específica (baseado no nome da pessoa)
    @Query("SELECT s FROM Split s JOIN s.people p WHERE LOWER(p.name) = LOWER(:name)")
    List<Split> findByPersonName(@Param("name") String name);

    // Buscar todos os Splits que possuem um template específico
    List<Split> findByTemplateIgnoreCase(String template);

    // Ordenar todos os Splits pela data (da mais recente para a mais antiga)
    @Query("SELECT s FROM Split s ORDER BY s.date DESC")
    List<Split> findAllOrderByDateDesc();

    // Ordenar todos os Splits pelo nome (ordem crescente)
    @Query("SELECT s FROM Split s ORDER BY s.name ASC")
    List<Split> findAllOrderByNameAsc();
}
