package com.br.rach.infrastructure.repositories;


import com.br.rach.domain.entities.SplitTemplate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SplitTemplateRepository extends JpaRepository<SplitTemplate, UUID> {
}
