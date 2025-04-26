package com.eemarisademello.eletiva_geotec_records.controller;

import com.eemarisademello.eletiva_geotec_client.dto.CategoryDTO;
import com.eemarisademello.eletiva_geotec_records.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping
    public List<CategoryDTO> getAllCategories() {
        return categoryService.getAll();
    }

    @GetMapping("/{id}")
    public CategoryDTO getCategoryById(@PathVariable Long id) {
        return categoryService.getById(id);
    }

    @GetMapping("/like/{categoryName}")
    public List<CategoryDTO> getCategoryByNameLike(@PathVariable String categoryName) {
        return categoryService.getByCategoryLike(categoryName);
    }

    @PostMapping
    public CategoryDTO saveCategory(@RequestBody CategoryDTO categoryDTO) {
        return categoryService.save(categoryDTO);
    }

    @PostMapping("/{id}")
    public CategoryDTO updateCategory(@PathVariable Long id, @RequestBody CategoryDTO categoryDTO) {
        return categoryService.update(id, categoryDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable Long id) {
        categoryService.delete(id);
    }

}
