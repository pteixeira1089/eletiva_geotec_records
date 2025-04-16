package com.eemarisademello.eletiva_geotec_records.controller;

import com.eemarisademello.eletiva_geotec_records.dto.FeelingDTO;
import com.eemarisademello.eletiva_geotec_records.service.FeelingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/feeling")
@RequiredArgsConstructor
public class FeelingController {
    private final FeelingService feelingService;

    @GetMapping
    public List<FeelingDTO> getAllFeelings() {
        return feelingService.getAll();
    }

    @GetMapping("/{id}")
    public FeelingDTO getFeeling(@PathVariable Long id) {
        return feelingService.getFeelingById(id);
    }

    @GetMapping("/like/{feeling}")
    public List<FeelingDTO> getFeelingLike(@PathVariable String feeling) {
        return feelingService.getByFeelingLike(feeling);
    }

    @PostMapping
    public FeelingDTO saveFeeling(@RequestBody FeelingDTO feelingDTO) {
        return feelingService.save(feelingDTO);
    }

    @PostMapping("/{id}")
    public FeelingDTO updateFeeling(@PathVariable Long id, @RequestBody FeelingDTO feelingDTO) {
        feelingDTO.setFeelingId(id);
        return feelingService.update(id,feelingDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteFeeling(@PathVariable Long id) {
        feelingService.delete(id);
    }
}
