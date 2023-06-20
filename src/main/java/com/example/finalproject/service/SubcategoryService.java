package com.example.finalproject.service;

import com.example.finalproject.dto.SimpleResponse.SimpleResponse;
import com.example.finalproject.dto.dtoSubcategory.SubcategoryRequest;
import com.example.finalproject.dto.dtoSubcategory.SubcategoryResponse;

import java.util.List;

public interface SubcategoryService {
    List<SubcategoryResponse> getAllSubcategory();
    SimpleResponse saveSubcategory(Long categoryId,SubcategoryRequest subcategoryRequest);
    SubcategoryResponse getSubcategoryById(Long subcategoryId);
    SimpleResponse updateSubcategory(Long subcategoryId,SubcategoryRequest subcategoryRequest);
    SimpleResponse deletedSubcategory(Long subcategoryId);
    SimpleResponse filterSubcategory(Long categoryId);
}
