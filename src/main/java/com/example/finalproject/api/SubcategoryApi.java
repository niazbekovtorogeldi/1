package com.example.finalproject.api;

import com.example.finalproject.dto.SimpleResponse.SimpleResponse;
import com.example.finalproject.dto.dtoSubcategory.SubcategoryRequest;
import com.example.finalproject.dto.dtoSubcategory.SubcategoryResponse;
import com.example.finalproject.repository.SubcategoryRepository;
import com.example.finalproject.service.SubcategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/subCategories")
public class SubcategoryApi {
    private final SubcategoryService subcategoryService;

    @GetMapping
    public List<SubcategoryResponse> subcategoryResponses() {
        return subcategoryService.getAllSubcategory();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/{categoryId}")
    public SimpleResponse save(@PathVariable Long categoryId, @RequestBody SubcategoryRequest subcategoryRequest) {
        return subcategoryService.saveSubcategory(categoryId, subcategoryRequest);
    }

    @GetMapping("/{subcategoryId}")
    public SubcategoryResponse getById(@PathVariable Long subcategoryId) {
        return subcategoryService.getSubcategoryById(subcategoryId);
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/{id}")
    public SimpleResponse update(@PathVariable Long id, @RequestBody SubcategoryRequest subcategoryRequest) {
        return subcategoryService.updateSubcategory(id, subcategoryRequest);
    }
    @DeleteMapping("/{id}")
    public SimpleResponse deleted(@PathVariable Long id){
        return subcategoryService.deletedSubcategory(id);
    }


}
