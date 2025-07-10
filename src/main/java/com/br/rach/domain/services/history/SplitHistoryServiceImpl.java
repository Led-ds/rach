package com.br.rach.domain.services.history;

import com.br.rach.application.resources.SplitHistoryControllerMapper;
import com.br.rach.domain.dtos.history.SplitHistoryCreateRequest;
import com.br.rach.domain.dtos.history.SplitHistoryDTO;
import com.br.rach.domain.entities.SplitHistory;
import com.br.rach.infrastructure.repositories.SplitHistoryRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class SplitHistoryServiceImpl implements SplitHistoryService {

    private final SplitHistoryRepository splitHistoryRepository;
    private final SplitHistoryControllerMapper splitHistoryControllerMapper;

    public SplitHistoryServiceImpl(SplitHistoryRepository splitHistoryRepository, SplitHistoryControllerMapper splitHistoryControllerMapper) {
        this.splitHistoryRepository = splitHistoryRepository;
        this.splitHistoryControllerMapper = splitHistoryControllerMapper;
    }

    @Override
    public List<SplitHistoryDTO> getAllSplitHistories() {
        return splitHistoryRepository.findAll()
                .stream()
                .map(splitHistoryControllerMapper::toModel)
                .collect(Collectors.toList());
    }

    @Override
    public SplitHistoryDTO getSplitHistoryById(UUID id) {
        return splitHistoryRepository.findById(id)
                .map(splitHistoryControllerMapper::toModel)
                .orElseThrow(() -> new RuntimeException("SplitHistory not found"));
    }

    @Override
    public void createSplitHistory(SplitHistoryCreateRequest splitHistoryCreateRequest) {
        SplitHistory splitHistory = splitHistoryControllerMapper.toEntity(splitHistoryCreateRequest);
        splitHistoryRepository.save(splitHistory);
    }

    @Override
    public void updateSplitHistory(UUID id, SplitHistoryCreateRequest splitHistoryCreateRequest) {
        SplitHistory splitHistory = splitHistoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("SplitHistory not found"));
        splitHistory = splitHistoryControllerMapper.toEntity(id, splitHistoryCreateRequest);
        splitHistoryRepository.save(splitHistory);
    }

    @Override
    public void deleteSplitHistory(UUID id) {
        splitHistoryRepository.deleteById(id);
    }

    public List<SplitHistoryDTO> getSplitHistoriesByStatus(String status) {
        return splitHistoryRepository.findByStatus(status)
                .stream()
                .map(splitHistoryControllerMapper::toModel)
                .collect(Collectors.toList());
    }

    public List<SplitHistoryDTO> getSplitHistoriesByDateRange(LocalDate startDate, LocalDate endDate) {
        return splitHistoryRepository.findByDateBetween(startDate, endDate)
                .stream()
                .map(splitHistoryControllerMapper::toModel)
                .collect(Collectors.toList());
    }
}
