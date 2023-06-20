package com.example.finalproject.repository;

import com.example.finalproject.dto.dtoCategory.CategoryResponse;
import com.example.finalproject.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category,Long> {
    @Query("select new com.example.finalproject.dto.dtoCategory.CategoryResponse(c.id,c.name)from Category c")
    List<CategoryResponse> getAllCategory();

    Optional<CategoryResponse> getCategoryById(Long categoryId);
}
