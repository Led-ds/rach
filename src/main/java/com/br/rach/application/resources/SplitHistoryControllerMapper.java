package com.br.rach.application.resources;

import com.br.rach.domain.dtos.history.SplitHistoryCreateRequest;
import com.br.rach.domain.dtos.history.SplitHistoryDTO;
import com.br.rach.domain.entities.SplitHistory;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.UUID;

@Mapper(componentModel = "spring")
public interface SplitHistoryControllerMapper {

    SplitHistoryDTO toModel(SplitHistory splitHistory);

    List<SplitHistoryDTO> toModel(List<SplitHistory> splitHistories);

    SplitHistory toEntity(SplitHistoryCreateRequest splitHistoryCreateRequest);

    @Mapping(target = "status", constant = "PENDING")
    SplitHistory toEntity(UUID id, SplitHistoryCreateRequest splitHistoryCreateRequest);

    SplitHistoryCreateRequest toCreateDto(SplitHistory splitHistory);
}
