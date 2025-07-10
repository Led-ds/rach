package com.br.rach.domain.services.history;

import com.br.rach.domain.dtos.history.SplitHistoryCreateRequest;
import com.br.rach.domain.dtos.history.SplitHistoryDTO;

import java.util.List;
import java.util.UUID;

public interface SplitHistoryService {
    List<SplitHistoryDTO> getAllSplitHistories();

    SplitHistoryDTO getSplitHistoryById(UUID id);

    void createSplitHistory(SplitHistoryCreateRequest splitHistoryCreateRequest);

    void updateSplitHistory(UUID id, SplitHistoryCreateRequest splitHistoryCreateRequest);

    void deleteSplitHistory(UUID id);
}
