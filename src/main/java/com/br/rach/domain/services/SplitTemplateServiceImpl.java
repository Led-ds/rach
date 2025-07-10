package com.br.rach.domain.services;

import com.br.rach.application.resources.SplitTemplateControllerMapper;
import com.br.rach.domain.dtos.template.SplitTemplateCreateRequest;
import com.br.rach.domain.dtos.template.SplitTemplateDTO;
import com.br.rach.domain.entities.SplitTemplate;
import com.br.rach.domain.services.template.SplitTemplateService;
import com.br.rach.infrastructure.repositories.SplitTemplateRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class SplitTemplateServiceImpl implements SplitTemplateService {

    private final SplitTemplateRepository splitTemplateRepository;
    private final SplitTemplateControllerMapper splitTemplateControllerMapper;

    public SplitTemplateServiceImpl(SplitTemplateRepository splitTemplateRepository, SplitTemplateControllerMapper splitTemplateControllerMapper) {
        this.splitTemplateRepository = splitTemplateRepository;
        this.splitTemplateControllerMapper = splitTemplateControllerMapper;
    }

    @Override
    public List<SplitTemplateDTO> getAllSplitTemplates() {
        return splitTemplateRepository.findAll()
                .stream()
                .map(splitTemplateControllerMapper::toModel)
                .collect(Collectors.toList());
    }

    @Override
    public SplitTemplateDTO getSplitTemplateById(UUID id) {
        return splitTemplateRepository.findById(id)
                .map(splitTemplateControllerMapper::toModel)
                .orElseThrow(() -> new RuntimeException("SplitTemplate not found"));
    }

    @Override
    public void createSplitTemplate(SplitTemplateCreateRequest splitTemplateCreateRequest) {
        SplitTemplate splitTemplate = splitTemplateControllerMapper.toEntity(splitTemplateCreateRequest);
        splitTemplateRepository.save(splitTemplate);
    }

    @Override
    public void updateSplitTemplate(UUID id, SplitTemplateCreateRequest splitTemplateCreateRequest) {
        SplitTemplate splitTemplate = splitTemplateRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("SplitTemplate not found"));
        splitTemplate = splitTemplateControllerMapper.toEntity(splitTemplateCreateRequest);
        splitTemplateRepository.save(splitTemplate);
    }

    @Override
    public void deleteSplitTemplate(UUID id) {
        splitTemplateRepository.deleteById(id);
    }
}
