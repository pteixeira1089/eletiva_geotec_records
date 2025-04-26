package com.eemarisademello.eletiva_geotec_records.converter;

import com.eemarisademello.eletiva_geotec_client.dto.CategoryDTO;
import com.eemarisademello.eletiva_geotec_client.dto.FeelingDTO;
import com.eemarisademello.eletiva_geotec_client.dto.RecordDTO;
import com.eemarisademello.eletiva_geotec_records.model.Category;
import com.eemarisademello.eletiva_geotec_records.model.Feeling;
import com.eemarisademello.eletiva_geotec_records.model.Record;

public class DTOConverter {

    public static CategoryDTO categoryToDTO(Category category) {
        if (category == null) {
            return null;
        }

        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setCategoryId(category.getCategoryId());
        categoryDTO.setCategory(category.getCategory());

        return categoryDTO;
    }

    public static FeelingDTO feelingToDTO(Feeling feeling) {
        if (feeling == null) {
            return null;
        }

        FeelingDTO feelingDTO = new FeelingDTO();
        feelingDTO.setFeelingId(feeling.getFeelingId());
        feelingDTO.setFeeling(feeling.getFeeling());

        return feelingDTO;
    }

    public static RecordDTO recordToDTO(Record record) {
        if (record == null) {
            return null;
        }

        RecordDTO recordDTO = new RecordDTO();
        recordDTO.setRecordId(record.getRecordId());
        recordDTO.setFeeling(feelingToDTO(record.getFeeling()));
        recordDTO.setCategory(categoryToDTO(record.getCategory()));
        recordDTO.setCreatedAt(record.getCreatedAt());
        recordDTO.setCreatedAt(record.getCreatedAt());

        return recordDTO;

    }

}
