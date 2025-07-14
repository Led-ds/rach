package com.br.rach.application.resources;

import com.br.rach.domain.dtos.split.SplitCreateRequestDTO;
import com.br.rach.domain.dtos.split.SplitDTO;
import com.br.rach.domain.entities.Split;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.UUID;

@Mapper(componentModel = "spring")
public interface SplitControllerMapper {

    SplitDTO toModel(Split split);

    List<SplitDTO> toModel(List<Split> splits);

    Split toEntity(SplitCreateRequestDTO splitCreateRequest);

    @Mapping(target = "status", constant = "PENDING")
    Split toEntity(UUID id, SplitCreateRequestDTO splitCreateRequest);

    SplitCreateRequestDTO toCreateDto(Split split);
}