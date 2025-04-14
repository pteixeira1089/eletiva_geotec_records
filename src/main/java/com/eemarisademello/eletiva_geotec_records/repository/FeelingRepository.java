package com.eemarisademello.eletiva_geotec_records.repository;

import com.eemarisademello.eletiva_geotec_records.model.Feeling;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeelingRepository extends JpaRepository<Feeling, Long> {
    Feeling findByFeelingId(Long feelingId);

    List<Feeling> queryByFeelingLike(String feeling);
}
