package com.eemarisademello.eletiva_geotec_records.service;

import com.eemarisademello.eletiva_geotec_records.dto.FeelingDTO;
import com.eemarisademello.eletiva_geotec_records.model.Feeling;
import com.eemarisademello.eletiva_geotec_records.repository.FeelingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;


@Service
@RequiredArgsConstructor
public class FeelingService {
    private final FeelingRepository feelingRepository;

    public List<FeelingDTO> getAll(){
        List<Feeling> feelings = feelingRepository.findAll();
        return feelings
                .stream()
                .map(FeelingDTO::convert)
                .toList();
    }

    public FeelingDTO getFeelingById(Long id){
        Feeling feeling = feelingRepository.findById(id).orElse(null);
        if (feeling != null) {
            return FeelingDTO.convert(feeling);
        }
        return null;
    }

    public List<FeelingDTO> getByFeelingLike(String feeling){
        List<Feeling> feelings = feelingRepository.queryByFeelingLike(feeling);
        return feelings
                .stream()
                .map(FeelingDTO::convert)
                .toList();
    }

    public void delete(Long id){
        Feeling feeling = feelingRepository.findById(id).orElse(null);
        if (feeling != null) {
            feelingRepository.delete(feeling);
        } else {
            throw new RuntimeException("Feeling not found");
        }
    }

    public FeelingDTO save(FeelingDTO feelingDTO){
        Feeling feeling = feelingRepository.save(Feeling.convert(feelingDTO));
        return FeelingDTO.convert(feeling);
    }

    public FeelingDTO update(Long id, FeelingDTO feelingDTO){
        //1. Validate the input
        if (feelingDTO == null) {
            throw new IllegalArgumentException("FeelingDTO cannot be null");
        }

        //2. Check if the feeling exists
        Feeling feeling = feelingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Feeling not found with ID: " + id));

        //3. Update the feeling if the new value is not null or empty
        if (StringUtils.hasText(feelingDTO.getFeeling())) {
            feeling.setFeeling(feelingDTO.getFeeling());
        }

        //4. Save the updated feeling
        return FeelingDTO.convert(feelingRepository.save(feeling));
    }
}
