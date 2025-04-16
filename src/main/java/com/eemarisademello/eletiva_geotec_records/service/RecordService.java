package com.eemarisademello.eletiva_geotec_records.service;

import com.eemarisademello.eletiva_geotec_records.dto.RecordDTO;
import com.eemarisademello.eletiva_geotec_records.model.Category;
import com.eemarisademello.eletiva_geotec_records.model.Feeling;
import com.eemarisademello.eletiva_geotec_records.model.Record;
import com.eemarisademello.eletiva_geotec_records.repository.CategoryRepository;
import com.eemarisademello.eletiva_geotec_records.repository.FeelingRepository;
import com.eemarisademello.eletiva_geotec_records.repository.RecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class RecordService {
    private final RecordRepository recordRepository;
    private final CategoryRepository categoryRepository;
    private final FeelingRepository feelingRepository;

    public List<RecordDTO> getAllRecords() {
        return recordRepository.findAll()
                .stream()
                .map(RecordDTO::convert)
                .toList();
    }

    public List<RecordDTO> getRecordsByUserId(Long userId) {
        return recordRepository.findRecordsByUserId(userId)
                .stream()
                .map(RecordDTO::convert)
                .toList();
    }

    public List<RecordDTO> getRecordsByCategoryId(Long categoryId) {
        return recordRepository.findRecordsByCategory(categoryId)
                .stream()
                .map(RecordDTO::convert)
                .toList();
    }

    public List<RecordDTO> getRecordsByFeelingId(Long feelingId) {
        return recordRepository.findRecordsByFeeling(feelingId)
                .stream()
                .map(RecordDTO::convert)
                .toList();
    }

    public RecordDTO getRecordById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID cannot be null");
        }

        return recordRepository
                .findById(id)
                .map(RecordDTO::convert)
                .orElseThrow(() -> new NoSuchElementException("No record found with ID: " + id));
    }

    public RecordDTO saveRecord(RecordDTO recordDTO) {
        //1. Validate the input
        if (recordDTO == null) {
            throw new IllegalArgumentException("RecordDTO cannot be null");
        }

        //2. Check if the feeling exists
        if (recordDTO.getFeeling() == null || recordDTO.getFeeling().getFeelingId() == null) {
            throw new IllegalArgumentException("Feeling ID cannot be null");
        }

        //3. Verify if the feeling exists in the database
        if (feelingRepository.findById(recordDTO.getFeeling().getFeelingId()).isEmpty()) {
            throw new NoSuchElementException("No feeling found with ID: " + recordDTO.getFeeling().getFeelingId());
        }

        //4. Convert and save
        Record record = Record.convert(recordDTO);
        return RecordDTO.convert(recordRepository.save(record));
    }

    public RecordDTO updateRecord(Long id, RecordDTO recordDTO) {
        //1. Validate the input
        if (recordDTO == null) {
            throw new IllegalArgumentException("RecordDTO cannot be null");
        }

        //2. Check if the record exists
        Record record = recordRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("No record found with ID: " + id));

        //3. Update the record if the new value is not null or empty
        if (recordDTO.getFeeling() != null && recordDTO.getFeeling().getFeelingId() != null) {
            Feeling feeling = feelingRepository
                    .findById(recordDTO.getFeeling().getFeelingId())
                    .orElseThrow(() -> new NoSuchElementException("No feeling found with ID: " + recordDTO.getFeeling().getFeelingId()));

            record.setFeeling(feeling);
        }

        //4. If the category is not null, verify if it exists
        if (recordDTO.getCategory() != null && recordDTO.getCategory().getCategoryId() != null) {
            Category category = categoryRepository
                    .findById(recordDTO.getCategory().getCategoryId())
                    .orElseThrow(() -> new NoSuchElementException("No category found with ID: " + recordDTO.getCategory().getCategoryId()));

            record.setCategory(category);
        }

        //5. Save the updated record
        return RecordDTO.convert(recordRepository.save(record));
    }

    public void deleteRecord(Long id) {
        Record record = recordRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("No record found with ID: " + id));
        recordRepository.delete(record);
    }


}
