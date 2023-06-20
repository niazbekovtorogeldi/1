package com.example.finalproject.dto.dtoCheque;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChequePaginationResponse {
    private List<ChequeResponse> menuItemResponseList;
    private int page;
    private int size;
}
