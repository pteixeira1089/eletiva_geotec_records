package com.eemarisademello.eletiva_geotec_records.repository;

import com.eemarisademello.eletiva_geotec_records.model.Category;
import com.eemarisademello.eletiva_geotec_records.model.Feeling;
import com.eemarisademello.eletiva_geotec_records.model.Record;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecordRepository extends JpaRepository<Record, Long> {
    List<Record> findRecordsByUserId(Long userId);

    List<Record> findRecordsByCategory (Category category);

    List<Record> findRecordsByFeeling(Feeling feeling);
}
