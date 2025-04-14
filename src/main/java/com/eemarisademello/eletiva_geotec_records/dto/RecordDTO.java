package com.eemarisademello.eletiva_geotec_records.dto;

import com.eemarisademello.eletiva_geotec_records.model.Record;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RecordDTO {

    @NotNull
    private Long recordId;

    @NotNull
    private Long userId;

    @NotBlank
    private String pictureUrl;

    @NotBlank
    private LocalDateTime createdAt;

    @NotNull
    private Double latitude;

    @NotNull
    private Double longitude;

    @NotNull
    private FeelingDTO feeling;

    @NotNull
    private CategoryDTO category;

    @NotBlank
    private String comment;

    public static RecordDTO convert(Record record) {
        RecordDTO recordDTO = new RecordDTO();
        recordDTO.setRecordId(record.getRecordId());
        recordDTO.setUserId(record.getUserId());
        recordDTO.setPictureUrl(record.getPictureUrl());
        recordDTO.setCreatedAt(record.getCreatedAt());
        recordDTO.setLatitude(record.getLatitude());
        recordDTO.setLongitude(record.getLongitude());
        recordDTO.setFeeling(FeelingDTO.convert(record.getFeeling()));
        recordDTO.setCategory(CategoryDTO.convert(record.getCategory()));
        recordDTO.setComment(record.getComment());
        return recordDTO;
    }
}
