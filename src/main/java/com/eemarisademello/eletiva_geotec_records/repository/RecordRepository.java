package com.eemarisademello.eletiva_geotec_records.repository;

import com.eemarisademello.eletiva_geotec_records.model.Category;
import com.eemarisademello.eletiva_geotec_records.model.Feeling;
import com.eemarisademello.eletiva_geotec_records.model.Record;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecordRepository extends JpaRepository<Record, Long> {
    List<Record> findRecordsByUserId(Long userId);

    @Query(value = "select r from Record r " +
            "where r.category.categoryId = :categoryId")
    List<Record> findRecordsByCategory(@Param("categoryId") Long categoryId);

    @Query(value = "select r from Record r " +
    "where r.feeling.feelingId = :feelingId")
    List<Record> findRecordsByFeeling(@Param("feelingId") Long feelingId);
}
