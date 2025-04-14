package com.eemarisademello.eletiva_geotec_records.repository;

import com.eemarisademello.eletiva_geotec_records.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findByCategoryId(Long categoryId);

    List<Category> queryByCategoryLike(String category);
}
