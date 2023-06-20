package com.example.finalproject.service;

import com.example.finalproject.dto.SimpleResponse.SimpleResponse;
import com.example.finalproject.dto.dtoCategory.CategoryRequest;
import com.example.finalproject.dto.dtoCategory.CategoryResponse;
import com.example.finalproject.entity.Category;

import java.util.List;

public interface CategoryService {
    List<CategoryResponse> getAllCategory();
    SimpleResponse saveCategory(CategoryRequest categoryRequest);
    CategoryResponse getCategoryById(Long categoryId);
    SimpleResponse updateCategory(Long categoryId,CategoryRequest categoryRequest);
    SimpleResponse deleted(Long categoryId);


}
