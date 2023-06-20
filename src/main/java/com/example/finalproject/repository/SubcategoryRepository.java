package com.example.finalproject.repository;

import com.example.finalproject.dto.SimpleResponse.SimpleResponse;
import com.example.finalproject.dto.dtoSubcategory.SubcategoryResponse;
import com.example.finalproject.entity.Subcategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface SubcategoryRepository extends JpaRepository<Subcategory,Long> {
    @Query("select new com.example.finalproject.dto.dtoSubcategory.SubcategoryResponse(s.id,s.name)from Subcategory s")
    List<SubcategoryResponse> getAllSubcategory();

    Optional<SubcategoryResponse> getSubcategoryById(Long subcategoryId);
    @Query("select s FROM Subcategory s where s.category=:categoryId order by s.name asc ")
    SimpleResponse filterSubcategory(Long categoryId);
}
