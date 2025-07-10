package com.br.rach.domain.services.split;

import com.br.rach.application.resources.SplitControllerMapper;
import com.br.rach.domain.dtos.split.SplitCreateRequestDTO;
import com.br.rach.domain.dtos.split.SplitDTO;
import com.br.rach.domain.entities.Split;
import com.br.rach.infrastructure.repositories.SplitRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class SplitServiceImpl implements SplitService {

    private final SplitRepository splitRepository;
    private final SplitControllerMapper splitControllerMapper;

    public SplitServiceImpl(SplitRepository splitRepository, SplitControllerMapper splitControllerMapper) {
        this.splitRepository = splitRepository;
        this.splitControllerMapper = splitControllerMapper;
    }

    @Override
    public List<SplitDTO> getAllSplits() {
        return splitRepository.findAll()
                .stream()
                .map(splitControllerMapper::toModel)
                .collect(Collectors.toList());
    }

    @Override
    public SplitDTO getSplitById(UUID id) {
        return splitRepository.findById(id)
                .map(splitControllerMapper::toModel)
                .orElseThrow(() -> new RuntimeException("Split not found"));
    }

    @Override
    public void createSplit(SplitCreateRequestDTO splitCreateRequest) {
        Split split = splitControllerMapper.toEntity(splitCreateRequest);
        splitRepository.save(split);
    }

    @Override
    public void updateSplit(UUID id, SplitCreateRequestDTO splitCreateRequest) {
        Split split = splitRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Split not found"));
        split = splitControllerMapper.toEntity(id, splitCreateRequest);
        splitRepository.save(split);
    }

    @Override
    public void deleteSplit(UUID id) {
        splitRepository.deleteById(id);
    }

    public List<SplitDTO> getSplitsByStatus(String status) {
        return splitRepository.findByStatus(status)
                .stream()
                .map(splitControllerMapper::toModel)
                .collect(Collectors.toList());
    }

    public List<SplitDTO> getSplitsByDateRange(LocalDate startDate, LocalDate endDate) {
        return splitRepository.findByDateBetween(startDate, endDate)
                .stream()
                .map(splitControllerMapper::toModel)
                .collect(Collectors.toList());
    }
}
