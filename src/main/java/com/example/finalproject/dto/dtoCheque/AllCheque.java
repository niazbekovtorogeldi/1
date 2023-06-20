package com.example.finalproject.dto.dtoCheque;

import com.example.finalproject.api.MenuItem;
import com.example.finalproject.dto.dtoMenultem.MenuItemResponse;
import lombok.Builder;

import java.util.List;
@Builder
public record AllCheque(
        String fullName,
        List<MenuItemResponse> items,
        Double price,
        Double Service,
        Double GrandTotal) {
    public AllCheque {
    }
}
