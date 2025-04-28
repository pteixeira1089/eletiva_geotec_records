package com.eemarisademello.eletiva_geotec_records.controller;

import com.eemarisademello.eletiva_geotec_client.dto.RecordDTO;
import com.eemarisademello.eletiva_geotec_records.service.RecordService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/record")
@AllArgsConstructor
public class RecordController {
    private final RecordService recordService;

    @GetMapping
    List<RecordDTO> getAllRecords() {
        return recordService.getAllRecords();
    }

    @GetMapping("/user/{userId}")
    List<RecordDTO> getRecordsByUserId(@PathVariable Long userId) {
        return recordService.getRecordsByUserId(userId);
    }

    @GetMapping("/category/{categoryId}")
    List<RecordDTO> getRecordsByCategoryId(@PathVariable Long categoryId) {
        return recordService.getRecordsByCategoryId(categoryId);
    }

    @GetMapping("/feeling/{feelingId}")
    List<RecordDTO> getRecordsByFeelingId(@PathVariable Long feelingId) {
        return recordService.getRecordsByFeelingId(feelingId);
    }

    @GetMapping("/{id}")
    RecordDTO getRecordById(@PathVariable Long id) {
        return recordService.getRecordById(id);
    }

    @PostMapping
    RecordDTO saveRecord(@RequestBody @Valid RecordDTO recordDTO) {
        recordDTO.setCreatedAt(LocalDateTime.now());
        return recordService.saveRecord(recordDTO);
    }

    @PostMapping("/{id}")
    RecordDTO updateRecord(@PathVariable Long id, @RequestBody RecordDTO recordDTO) {
        return recordService.updateRecord(id, recordDTO);
    }

    @DeleteMapping("/{id}")
    void deleteRecord(Long id) {
        recordService.deleteRecord(id);
    }


}
