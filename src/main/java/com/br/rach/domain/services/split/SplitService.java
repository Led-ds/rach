package com.br.rach.domain.services.split;

import com.br.rach.domain.dtos.split.SplitCreateRequestDTO;
import com.br.rach.domain.dtos.split.SplitDTO;

import java.util.List;
import java.util.UUID;

public interface SplitService {

    List<SplitDTO> getAllSplits();

    SplitDTO getSplitById(UUID id);

    void createSplit(SplitCreateRequestDTO splitCreateRequest);

    void updateSplit(UUID id, SplitCreateRequestDTO splitCreateRequest);

    void deleteSplit(UUID id);

}
