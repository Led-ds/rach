package com.br.rach.infrastructure.repositories;

import com.br.rach.domain.entities.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, UUID> {

    // Encontrar uma despesa pela descrição (ignorando maiúsculas/minúsculas)
    Optional<Expense> findByDescriptionIgnoreCase(String description);

    // Buscar despesas com a descrição contendo o nome fornecido (case insensitive)
    @Query("SELECT e FROM Expense e WHERE LOWER(e.description) LIKE LOWER(CONCAT('%', :description, '%'))")
    List<Expense> findByDescriptionContainingIgnoreCase(@Param("description") String description);

    // Verificar se uma despesa com a descrição já existe (excluindo a despesa com o ID fornecido)
    @Query("SELECT COUNT(e) > 0 FROM Expense e WHERE LOWER(e.description) = LOWER(:description) AND e.id != :id")
    boolean existsByDescriptionIgnoreCaseAndIdNot(@Param("description") String description, @Param("id") Long id);

    // Verificar se uma despesa com a descrição já existe
    @Query("SELECT COUNT(e) > 0 FROM Expense e WHERE LOWER(e.description) = LOWER(:description)")
    boolean existsByDescriptionIgnoreCase(@Param("description") String description);

    // Encontrar despesas que foram pagas por uma pessoa específica (nome do pagador)
    List<Expense> findByPaidByIgnoreCase(String paidBy);

    // Buscar despesas por categoria (case insensitive)
    List<Expense> findByCategoryIgnoreCase(String category);

    // Buscar despesas que ocorreram em um intervalo de datas
    List<Expense> findByDateBetween(LocalDate startDate, LocalDate endDate);

    // Buscar despesas com valores acima de um determinado valor
    @Query("SELECT e FROM Expense e WHERE e.amount > :amount")
    List<Expense> findByAmountGreaterThan(@Param("amount") BigDecimal amount);

    // Ordenar todas as despesas por data, da mais recente para a mais antiga
    @Query("SELECT e FROM Expense e ORDER BY e.date DESC")
    List<Expense> findAllOrderByDateDesc();

    // Ordenar todas as despesas por valor, do maior para o menor
    @Query("SELECT e FROM Expense e ORDER BY e.amount DESC")
    List<Expense> findAllOrderByAmountDesc();
}
