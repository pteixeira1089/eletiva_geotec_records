package com.eemarisademello.eletiva_geotec_records.service;

import com.eemarisademello.eletiva_geotec_records.converter.DTOConverter;
import com.eemarisademello.eletiva_geotec_records.model.Category;
import com.eemarisademello.eletiva_geotec_client.dto.CategoryDTO;
import com.eemarisademello.eletiva_geotec_records.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public List<CategoryDTO> getAll() {
        List<Category> categories = categoryRepository.findAll();
        return categories
                .stream()
                .map(DTOConverter::categoryToDTO)
                .collect(Collectors.toList());
    }

    public CategoryDTO getById(Long id) {
        Category category = categoryRepository.findById(id).orElse(null);
        if (category != null) {
            return DTOConverter.categoryToDTO(category);
        }
        return null;
    }

    public List<CategoryDTO> getByCategoryLike(String category) {
        List<Category> categories = categoryRepository.findByCategoryContainingIgnoreCase(category);
        return categories
                .stream()
                .map(DTOConverter::categoryToDTO)
                .collect(Collectors.toList());
    }

    public CategoryDTO save(CategoryDTO categoryDTO) {
        Category category = new Category();

        //Only set ID if explicitly provided (for update)
        if (categoryDTO.getCategoryId() != null) {
            category.setCategoryId(categoryDTO.getCategoryId());
        }

        category.setCategory(categoryDTO.getCategory());

        Category savedCategory = categoryRepository.save(category);

        return DTOConverter.categoryToDTO(savedCategory);
    }

    public void delete(Long id) {
        Optional<Category> category = categoryRepository.findById(id);
        if (category.isPresent()) {
            categoryRepository.delete(category.get());
        } else {
            throw new RuntimeException("Category not found");
        }
    }

    public CategoryDTO update(Long id, CategoryDTO categoryDTO) {
        //1. Validate the input
        if (categoryDTO == null) {
            throw new IllegalArgumentException("CategoryDTO cannot be null");
        }

        //2. Check if the category exists
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found with ID: " + id));

        //3. Update the category if the new value is not null or empty
        if (StringUtils.hasText(categoryDTO.getCategory())) {
            category.setCategory(categoryDTO.getCategory());
        }

        //4. Save the updated category
        return DTOConverter.categoryToDTO(categoryRepository.save(category));
        }
}
