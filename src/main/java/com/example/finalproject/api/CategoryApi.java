package com.example.finalproject.api;

import com.example.finalproject.dto.SimpleResponse.SimpleResponse;
import com.example.finalproject.dto.dtoCategory.CategoryRequest;
import com.example.finalproject.dto.dtoCategory.CategoryResponse;
import com.example.finalproject.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/categories")
public class CategoryApi {
    private final CategoryService categoryService;

    @GetMapping
    public List<CategoryResponse> getAllCategory(){
        return categoryService.getAllCategory();
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public SimpleResponse saveCategory( @RequestBody CategoryRequest categoryRequest){
        return categoryService.saveCategory(categoryRequest);
    }

    @GetMapping("/find/{id}")
    public CategoryResponse getCategoryById(@PathVariable Long id){
        return categoryService.getCategoryById(id);
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/{categoryId}")
    public SimpleResponse updateCategory(@PathVariable Long categoryId,@RequestBody CategoryRequest categoryRequest){
        return categoryService.updateCategory(categoryId,categoryRequest);
    }
    @DeleteMapping("/{categoryId}")
    public SimpleResponse deleted(@PathVariable Long categoryId){
        return categoryService.deleted(categoryId);
    }
}
