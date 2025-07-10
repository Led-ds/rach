package com.br.rach.domain.services.template;

import com.br.rach.domain.dtos.template.SplitTemplateCreateRequest;
import com.br.rach.domain.dtos.template.SplitTemplateDTO;

import java.util.List;
import java.util.UUID;

public interface SplitTemplateService {

    List<SplitTemplateDTO> getAllSplitTemplates();

    SplitTemplateDTO getSplitTemplateById(UUID id);

    void createSplitTemplate(SplitTemplateCreateRequest splitTemplateCreateRequest);

    void updateSplitTemplate(UUID id, SplitTemplateCreateRequest splitTemplateCreateRequest);

    void deleteSplitTemplate(UUID id);
}