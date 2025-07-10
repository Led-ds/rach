package com.br.rach.application.resources;

import com.br.rach.domain.dtos.split.SplitCreateRequestDTO;
import com.br.rach.domain.dtos.split.SplitDTO;
import com.br.rach.domain.services.split.SplitService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/splits")
public class SplitController {

    private final SplitService splitService;
    private final SplitControllerMapper splitControllerMapper;

    public SplitController(SplitService splitService, SplitControllerMapper splitControllerMapper) {
        this.splitService = splitService;
        this.splitControllerMapper = splitControllerMapper;
    }

    @GetMapping
    public List<SplitDTO> getAllSplits() {
        return splitService.getAllSplits();
    }

    @GetMapping("/{id}")
    public SplitDTO getSplitById(@PathVariable UUID id) {
        return splitService.getSplitById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createSplit(@RequestBody SplitCreateRequestDTO splitCreateRequest) {
        splitService.createSplit(splitCreateRequest);
    }

    @PutMapping("/{id}")
    public void updateSplit(@PathVariable UUID id, @RequestBody SplitCreateRequestDTO splitCreateRequest) {
        splitService.updateSplit(id, splitCreateRequest);
    }

    @DeleteMapping("/{id}")
    public void deleteSplit(@PathVariable UUID id) {
        splitService.deleteSplit(id);
    }
}