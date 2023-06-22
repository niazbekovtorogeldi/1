package com.example.finalproject.repository;


import com.example.finalproject.dto.dtoCategory.CategoryResponse;
import com.example.finalproject.dto.dtoMenultem.MenuItemResponse;
import com.example.finalproject.dto.dtoSubcategory.SubcategoryResponse;
import com.example.finalproject.entity.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;


public interface MenuItemRepository extends JpaRepository<MenuItem, Long> {

    @Query("select new com.example.finalproject.dto.dtoMenultem.MenuItemResponse(m.id,m.name,m.image,m.price,m.description,m.isVegetarian) from MenuItem m")
    List<MenuItemResponse> getAllMenuItem();
    @Query("select new com.example.finalproject.dto.dtoMenultem.MenuItemResponse" +
            "(m.id,m.name,m.image,m.price,m.description,m.isVegetarian)" +
            " from MenuItem m where m.isVegetarian=:isVegetarian")
    List<MenuItemResponse> getMenuItemByFilterByVegetarian(@Param("isVegetarian") Boolean isVegetarian);

    @Query("SELECT new com.example.finalproject.dto.dtoMenultem.MenuItemResponse(" +
            "        m.id, m.name, m.image, m.price, m.description, m.isVegetarian)" +
            "         FROM MenuItem m" +
            "         ORDER BY CASE WHEN :sortOrder = 'asc' THEN m.price END ASC," +
            "         CASE WHEN :sortOrder = 'desc' THEN m.price END DESC")
    List<MenuItemResponse> getMenuItemByAscOrDesc(@Param("sortOrder") String sortOrder);

    @Query("select new com.example.finalproject.dto.dtoMenultem.MenuItemResponse" +
            "(m.id,m.name,m.image,m.price,m.description,m.isVegetarian)" +
            " from MenuItem m where m.name like concat('%',:word,'%')")
    List<MenuItemResponse> searchMenuItemWords(@Param("word") String word);

    @Query("select new com.example.finalproject.dto.dtoSubcategory.SubcategoryResponse(s.id,s.name)" +
            "from Subcategory s where s.name like concat('%',:word,'%') ")
    List<SubcategoryResponse> searchSubCategoryWords(@Param("word") String word);
    @Query("select new com.example.finalproject.dto.dtoCategory.CategoryResponse(c.id,c.name)" +
            "from Category  c where c.name like concat('%',:word,'%') ")
    List<CategoryResponse> searchCategoryByWords(@Param("word") String word);

    Optional<MenuItemResponse> getMenuItemById(Long menuItemId);
}
