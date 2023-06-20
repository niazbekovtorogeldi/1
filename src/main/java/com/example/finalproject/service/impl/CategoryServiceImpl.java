package com.example.finalproject.service.impl;

import com.example.finalproject.dto.SimpleResponse.SimpleResponse;
import com.example.finalproject.dto.dtoCategory.CategoryRequest;
import com.example.finalproject.dto.dtoCategory.CategoryResponse;
import com.example.finalproject.entity.Category;
import com.example.finalproject.repository.CategoryRepository;
import com.example.finalproject.service.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    @Override
    public List<CategoryResponse> getAllCategory() {
        return categoryRepository.getAllCategory();
    }

    @Override
    public SimpleResponse saveCategory(CategoryRequest categoryRequest) {
        Category category = new Category();
        category.setName(categoryRequest.name());
        categoryRepository.save(category);
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message(String.format("save Category"))
                .build();
    }

    @Override
    public CategoryResponse getCategoryById(Long categoryId) {
        return categoryRepository.getCategoryById(categoryId).orElseThrow(()->new NullPointerException(""));
    }

    @Override
    public SimpleResponse updateCategory(Long categoryId, CategoryRequest categoryRequest) {
        Category category =categoryRepository.findById(categoryId).orElseThrow(()->new NullPointerException("not category id"+categoryId));
        category.setName(categoryRequest.name());
        categoryRepository.save(category);
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message(String.format("updated category id"+categoryId))
                .build();
    }

    @Override
    public SimpleResponse deleted(Long categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(()->new NullPointerException("deleted category"+categoryId));
        categoryRepository.delete(category);
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message(String.format("deleted category id"+categoryId))
                .build();
    }
}
