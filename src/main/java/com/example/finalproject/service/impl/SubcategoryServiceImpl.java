package com.example.finalproject.service.impl;

import com.example.finalproject.dto.SimpleResponse.SimpleResponse;
import com.example.finalproject.dto.dtoSubcategory.SubcategoryRequest;
import com.example.finalproject.dto.dtoSubcategory.SubcategoryResponse;
import com.example.finalproject.entity.Category;
import com.example.finalproject.entity.Subcategory;
import com.example.finalproject.repository.CategoryRepository;
import com.example.finalproject.repository.SubcategoryRepository;
import com.example.finalproject.service.SubcategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class SubcategoryServiceImpl implements SubcategoryService {
    private final SubcategoryRepository subcategoryRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public List<SubcategoryResponse> getAllSubcategory() {
        return subcategoryRepository.getAllSubcategory();
    }

    @Override
    public SimpleResponse saveSubcategory(Long categoryId, SubcategoryRequest subcategoryRequest) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new NullPointerException("not category" + categoryId));
        Subcategory subcategory = new Subcategory();
        subcategory.setName(subcategoryRequest.name());
        List<Subcategory> subcategories = new ArrayList<>();
        subcategories.add(subcategory);
        subcategory.setCategory(category);
        category.setSubcategories(subcategories);
        subcategoryRepository.save(subcategory);
        categoryRepository.save(category);

        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message(String.format("save subcategory" + category.getName()))
                .build();
    }

    @Override
    public SubcategoryResponse getSubcategoryById(Long subcategoryId) {
       return subcategoryRepository.getSubcategoryById(subcategoryId).orElseThrow(()->new NullPointerException(""));

    }

    @Override
    public SimpleResponse updateSubcategory(Long subcategoryId, SubcategoryRequest subcategoryRequest) {
        Subcategory subcategory = subcategoryRepository.findById(subcategoryId).orElseThrow(()->new NullPointerException(""));
        subcategory.setName(subcategoryRequest.name());
        subcategoryRepository.save(subcategory);
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message(String.format("updated subcategory"))
                .build();
    }

    @Override
    public SimpleResponse deletedSubcategory(Long subcategoryId) {
        Subcategory subcategory = subcategoryRepository.findById(subcategoryId).orElseThrow(() -> new NullPointerException("not subcategory" + subcategoryId));
        subcategoryRepository.delete(subcategory);
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message(String.format("deleted "+subcategoryId))
                .build();

    }

    @Override
    public SimpleResponse filterSubcategory(Long categoryId) {
        return subcategoryRepository.filterSubcategory(categoryId);
    }
}
