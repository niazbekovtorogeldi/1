package com.example.finalproject.service.impl;

import com.example.finalproject.confic.JwtService;
import com.example.finalproject.dto.SimpleResponse.SimpleResponse;
import com.example.finalproject.dto.dtoCategory.CategoryResponse;
import com.example.finalproject.dto.dtoMenultem.MenuItemRequest;
import com.example.finalproject.dto.dtoMenultem.MenuItemResponse;
import com.example.finalproject.dto.dtoSubcategory.SubcategoryResponse;
import com.example.finalproject.entity.MenuItem;
import com.example.finalproject.entity.Restaurant;
import com.example.finalproject.entity.Subcategory;
import com.example.finalproject.exception.NotFoundException;
import com.example.finalproject.repository.MenuItemRepository;
import com.example.finalproject.repository.RestaurantRepository;
import com.example.finalproject.repository.SubcategoryRepository;
import com.example.finalproject.service.MenuItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class MenuItemServiceImpl implements MenuItemService {
    private final RestaurantRepository restaurantRepository;
    private final SubcategoryRepository subcategoryRepository;
    private final MenuItemRepository menuItemRepository;

    @Override
    public List<MenuItemResponse> getAllMenuItem() {
        return menuItemRepository.getAllMenuItem();
    }
    @Override
    public List<MenuItemResponse>searchMenuItemWords(String word){
        return menuItemRepository.searchMenuItemWords(word);
    }
    @Override
    public List<MenuItemResponse>getMenuItemByFilterByVegetarian(Boolean isVegetarian){
        return menuItemRepository.getMenuItemByFilterByVegetarian(isVegetarian);
    }
    @Override
    public List<MenuItemResponse>getMenuItemByAscOrDesc(String word){
        return menuItemRepository.getMenuItemByAscOrDesc(word);
    }
    @Override
    public List<SubcategoryResponse>searchSubCategoryWords(String word){
        return menuItemRepository.searchSubCategoryWords(word);
    }
    @Override
    public List<CategoryResponse>searchCategoryByWords(String word){
        return menuItemRepository.searchCategoryByWords(word);
    }


    @Override
    public SimpleResponse saveMenuItem(Long restaurantId, MenuItemRequest menultemRequest) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new UsernameNotFoundException
                        (String.format("Restaurant with id %s doesn't exist !",restaurantId)));
        MenuItem menuItem = new MenuItem();
        menuItem.setName(menultemRequest.name());
        menuItem.setImage(menultemRequest.image());
        menuItem.setPrice(menultemRequest.price());
        menuItem.setDescription(menultemRequest.description());
        menuItem.setVegetarian(menultemRequest.isVegetarian());
        List<MenuItem> menuItems = new ArrayList<>();
        menuItems.add(menuItem);
        restaurant.setMenuItem(menuItems);
        menuItem.setRestaurant(restaurant);


        menuItemRepository.save(menuItem);
        restaurantRepository.save(restaurant);

        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message(String.format("MenuItem with name %s  successfully saved ! ", menuItem.getName()))
                .build();

    }

    @Override
    public MenuItemResponse getMenuItemById(Long menuItemId) {
        return menuItemRepository.getMenuItemById(menuItemId).orElseThrow(()->new NullPointerException("not menuItem"+menuItemId));
    }

    @Override
    public SimpleResponse updateMenuItem(Long menuItemId,MenuItemRequest menuItemRequest) {
        MenuItem menuItem1 = menuItemRepository.findById(menuItemId).orElseThrow(()->new NullPointerException("not menuItem"+menuItemId));
        menuItem1.setName(menuItemRequest.name());
        menuItem1.setImage(menuItemRequest.image());
        menuItem1.setPrice(menuItemRequest.price());
        menuItem1.setDescription(menuItemRequest.description());
        menuItem1.setVegetarian(menuItemRequest.isVegetarian());
        menuItemRepository.save(menuItem1);
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message(String.format("updated new menuItem"))
                .build();
    }

    @Override
    public SimpleResponse assineSubcategoryId(Long subCategoryId, Long menuId) {
       Subcategory subcategory = subcategoryRepository.findById(subCategoryId).orElseThrow(()->new NullPointerException(""));
       MenuItem menuItem = menuItemRepository.findById(menuId).orElseThrow(()->new NullPointerException(""));
       menuItem.setSubcategory(subcategory);
       List<MenuItem>menuItems = new ArrayList<>();
       menuItems.add(menuItem);
       subcategory.setMenuItems(menuItems);
       menuItemRepository.save(menuItem);
       subcategoryRepository.save(subcategory);
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message(String.format("assine"+menuId))
                .build();
    }


    @Override
    public SimpleResponse deleted(Long menuItemId) {
     MenuItem menuItem=menuItemRepository.findById(menuItemId).orElseThrow(()-> {
                    log.error("MenuItem with id  " + menuItemId + "  not found !");
                    return new NotFoundException("MenuItem with id  " + menuItemId + "  not found !");
                });
        menuItemRepository.delete(menuItem);
        log.info(String.format("MenuItem with name %s successfully deleted ! ", menuItem.getName()));
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message(String.format("MenuItem with name %s successfully deleted ! ", menuItem.getName()))
                .build();
    }

}
