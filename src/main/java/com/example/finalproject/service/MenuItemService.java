package com.example.finalproject.service;

import com.example.finalproject.dto.SimpleResponse.SimpleResponse;
import com.example.finalproject.dto.dtoCategory.CategoryResponse;
import com.example.finalproject.dto.dtoMenultem.MenuItemRequest;
import com.example.finalproject.dto.dtoMenultem.MenuItemResponse;
import com.example.finalproject.dto.dtoSubcategory.SubcategoryResponse;

import java.util.List;

public interface MenuItemService {
    List<MenuItemResponse>getAllMenuItem();

    List<MenuItemResponse>searchMenuItemWords(String word);

    List<MenuItemResponse>getMenuItemByFilterByVegetarian(Boolean isVegetarian);

    List<MenuItemResponse>getMenuItemByAscOrDesc(String word);

    List<SubcategoryResponse>searchSubCategoryWords(String word);

    List<CategoryResponse>searchCategoryByWords(String word);

    SimpleResponse saveMenuItem(Long restaurantId, MenuItemRequest menuitemRequest);
    MenuItemResponse getMenuItemById(Long menuItemId);
    SimpleResponse updateMenuItem(Long menuItemId,MenuItemRequest menuItemRequest);
    SimpleResponse assineSubcategoryId(Long subCategoryId,Long menuId);
    SimpleResponse deleted(Long menuItemId);

}
