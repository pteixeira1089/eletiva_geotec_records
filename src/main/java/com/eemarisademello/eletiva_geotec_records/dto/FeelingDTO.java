package com.eemarisademello.eletiva_geotec_records.dto;

import com.eemarisademello.eletiva_geotec_records.model.Feeling;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FeelingDTO {
    private Long feelingId;

    @NotBlank
    private String feeling;

    public static FeelingDTO convert(Feeling feeling) {
        FeelingDTO feelingDTO = new FeelingDTO();
        feelingDTO.setFeelingId(feeling.getFeelingId());
        feelingDTO.setFeeling(feeling.getFeeling());
        return feelingDTO;
    }
}
