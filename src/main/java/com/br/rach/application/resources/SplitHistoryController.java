package com.br.rach.application.resources;

import com.br.rach.domain.dtos.history.SplitHistoryCreateRequest;
import com.br.rach.domain.dtos.history.SplitHistoryDTO;
import com.br.rach.domain.services.history.SplitHistoryService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/split-history")
public class SplitHistoryController {

    private final SplitHistoryService splitHistoryService;

    public SplitHistoryController(SplitHistoryService splitHistoryService, SplitHistoryControllerMapper splitHistoryControllerMapper) {
        this.splitHistoryService = splitHistoryService;
    }

    @GetMapping
    public List<SplitHistoryDTO> getAllSplitHistories() {
        return splitHistoryService.getAllSplitHistories();
    }

    @GetMapping("/{id}")
    public SplitHistoryDTO getSplitHistoryById(@PathVariable UUID id) {
        return splitHistoryService.getSplitHistoryById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createSplitHistory(@RequestBody SplitHistoryCreateRequest splitHistoryCreateRequest) {
        splitHistoryService.createSplitHistory(splitHistoryCreateRequest);
    }

    @PutMapping("/{id}")
    public void updateSplitHistory(@PathVariable UUID id, @RequestBody SplitHistoryCreateRequest splitHistoryCreateRequest) {
        splitHistoryService.updateSplitHistory(id, splitHistoryCreateRequest);
    }

    @DeleteMapping("/{id}")
    public void deleteSplitHistory(@PathVariable UUID id) {
        splitHistoryService.deleteSplitHistory(id);
    }
}
