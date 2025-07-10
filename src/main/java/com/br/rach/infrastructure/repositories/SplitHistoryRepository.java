package com.br.rach.infrastructure.repositories;

import com.br.rach.domain.entities.SplitHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface SplitHistoryRepository extends JpaRepository<SplitHistory, UUID> {

    // Encontrar um SplitHistory pelo nome (ignorando maiúsculas/minúsculas)
    Optional<SplitHistory> findByNameIgnoreCase(String name);

    // Buscar SplitHistorys com o nome contendo o termo fornecido (case insensitive)
    @Query("SELECT s FROM SplitHistory s WHERE LOWER(s.name) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<SplitHistory> findByNameContainingIgnoreCase(@Param("name") String name);

    // Verificar se já existe um SplitHistory com o nome fornecido (excluindo o SplitHistory com o ID fornecido)
    @Query("SELECT COUNT(s) > 0 FROM SplitHistory s WHERE LOWER(s.name) = LOWER(:name) AND s.id != :id")
    boolean existsByNameIgnoreCaseAndIdNot(@Param("name") String name, @Param("id") Long id);

    // Verificar se já existe um SplitHistory com o nome fornecido
    @Query("SELECT COUNT(s) > 0 FROM SplitHistory s WHERE LOWER(s.name) = LOWER(:name)")
    boolean existsByNameIgnoreCase(@Param("name") String name);

    // Buscar SplitHistorys por status (PENDING ou COMPLETED)
    List<SplitHistory> findByStatus(String status);

    // Buscar SplitHistorys por data específica
    List<SplitHistory> findByDate(LocalDate date);

    // Buscar SplitHistorys em um intervalo de datas
    List<SplitHistory> findByDateBetween(LocalDate startDate, LocalDate endDate);

    // Buscar SplitHistorys relacionados a uma pessoa (baseado no nome da pessoa)
    @Query("SELECT s FROM SplitHistory s JOIN s.people p WHERE LOWER(p.name) = LOWER(:name)")
    List<SplitHistory> findByPersonName(@Param("name") String name);

    // Buscar SplitHistorys que possuem um template específico
    List<SplitHistory> findByTemplateIgnoreCase(String template);

    // Ordenar todos os SplitHistorys pela data, da mais recente para a mais antiga
    @Query("SELECT s FROM SplitHistory s ORDER BY s.date DESC")
    List<SplitHistory> findAllOrderByDateDesc();

    // Ordenar todos os SplitHistorys pelo nome (ordem crescente)
    @Query("SELECT s FROM SplitHistory s ORDER BY s.name ASC")
    List<SplitHistory> findAllOrderByNameAsc();
}
