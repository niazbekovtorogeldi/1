package com.example.finalproject.api;

import com.example.finalproject.dto.SimpleResponse.SimpleResponse;
import com.example.finalproject.dto.dtoCategory.CategoryResponse;
import com.example.finalproject.dto.dtoMenultem.MenuItemRequest;
import com.example.finalproject.dto.dtoMenultem.MenuItemResponse;
import com.example.finalproject.dto.dtoSubcategory.SubcategoryResponse;
import com.example.finalproject.service.MenuItemService;
import lombok.RequiredArgsConstructor;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/menuItem")

public class MenuItem {


    private final MenuItemService menuItemService;

    @GetMapping
    public List<MenuItemResponse>getAll(){
        return menuItemService.getAllMenuItem();
    }
    @GetMapping("/search")
    public List<MenuItemResponse>searchMenuItemWords(@RequestParam String word){
        return menuItemService.searchMenuItemWords(word);
    }
    @GetMapping("/filter")
    public List<MenuItemResponse>getMenuItemByFilterByVegetarian(@RequestParam Boolean isVegetarian){
        return menuItemService.getMenuItemByFilterByVegetarian(isVegetarian);
    }
    @GetMapping("/AscOrDesc")
    public List<MenuItemResponse>getMenuItemByAscOrDesc(@RequestParam String word){
        return menuItemService.getMenuItemByAscOrDesc(word);
    }

    @GetMapping("/searchesSubCategory")
    public List<SubcategoryResponse>searchSubCategoryWords(@RequestParam String word){
        return menuItemService.searchSubCategoryWords(word);
    }
    @GetMapping("/searchCategory")
    public  List<CategoryResponse>searchCategoryByWords(@RequestParam String word){
        return menuItemService.searchCategoryByWords(word);
    }


    @PreAuthorize("hasAnyAuthority('ADMIN','CHEF')")
    @PostMapping("/save/{restaurantId}")
    public SimpleResponse save(@PathVariable Long restaurantId, @RequestBody MenuItemRequest menuitemRequest){
        return menuItemService.saveMenuItem(restaurantId,menuitemRequest);
    }
    @GetMapping("/{id}")
    public MenuItemResponse getById(@PathVariable Long id){
        return menuItemService.getMenuItemById(id);

    }
    @PreAuthorize("hasAnyAuthority('ADMIN','CHEF')")
    @PutMapping("/update/{updateId}")
    public SimpleResponse update(@PathVariable Long updateId ,@RequestBody MenuItemRequest menuItemRequest ){
        return menuItemService.updateMenuItem(updateId,menuItemRequest);
    }
    @PreAuthorize("hasAnyAuthority('ADMIN','CHEF')")
    @PostMapping("/assine/{subCategoryId}/{menuId}")
    public SimpleResponse assine(@PathVariable Long subCategoryId,@PathVariable Long menuId){
        return menuItemService.assineSubcategoryId(subCategoryId,menuId);
    }

    @DeleteMapping("/{menuItemId}")
    public SimpleResponse deleted(@PathVariable Long menuItemId){
        return menuItemService.deleted(menuItemId);
    }


}
