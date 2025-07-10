package com.br.rach.application.resources;

import com.br.rach.domain.dtos.template.SplitTemplateCreateRequest;
import com.br.rach.domain.dtos.template.SplitTemplateDTO;
import com.br.rach.domain.entities.SplitTemplate;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SplitTemplateControllerMapper {
    SplitTemplateDTO toModel(SplitTemplate splitTemplate);

    List<SplitTemplateDTO> toModel(List<SplitTemplate> splitTemplates);

    SplitTemplate toEntity(SplitTemplateCreateRequest splitTemplateCreateRequest);

    SplitTemplateCreateRequest toCreateDto(SplitTemplate splitTemplate);
}
