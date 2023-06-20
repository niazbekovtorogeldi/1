package com.example.finalproject.api;

import com.example.finalproject.dto.SimpleResponse.SimpleResponse;
import com.example.finalproject.dto.dtoCategory.CategoryResponse;
import com.example.finalproject.dto.dtoCheque.AllCheque;
import com.example.finalproject.dto.dtoCheque.ChequePaginationResponse;
import com.example.finalproject.dto.dtoCheque.ChequeRequest;
import com.example.finalproject.dto.dtoCheque.ChequeResponse;
import com.example.finalproject.service.ChequeService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cheque")
@RequiredArgsConstructor
public class ChequeApi {
    private final ChequeService chequeService ;
    @GetMapping("/getAll/{id}")
    public List<ChequeResponse>getAll(@PathVariable Long id){
        return chequeService.getAllChequesByUserId(id);
    }
    @PreAuthorize("hasAnyAuthority('ADMIN','WAITER')")
    @PostMapping("/save/{userId}/{restoranId}")
    public SimpleResponse save(@PathVariable Long userId,@PathVariable Long restoranId,@RequestBody ChequeRequest chequeRequest){
        return chequeService.saveCheque(userId,restoranId,chequeRequest);
    }
    @GetMapping("/{id}")
    public ChequeResponse getById(@PathVariable Long id){
        return chequeService.getChequeById(id);

    }
    @PutMapping("/update/{id}")
    public SimpleResponse update(@PathVariable Long id,@RequestBody ChequeRequest chequeRequest){
        return chequeService.updateCheque(id,chequeRequest);
    }
    @DeleteMapping("/{id}")
    public SimpleResponse deleted(@PathVariable Long id){
        return chequeService.deletedId(id);

    }
    @GetMapping("/all/{id}")
    List<AllCheque> getAllInformationFromUser(@PathVariable Long id) {
        return chequeService.getFullInformationFromUser(id);
    }

    @GetMapping("/sum/{restaurantId}")
    public Double getAllChecksSumsFromRestaurant(@PathVariable Long restaurantId) {
        return chequeService.getAllChecksSumsFromRestaurant(restaurantId);

    }

    @GetMapping("/page/{id}")
    public ChequePaginationResponse getAllByPagination(@PathVariable Long id,
                                                       @RequestParam int page,
                                                       @RequestParam int size){
        return chequeService.getPagination(id,page,size);

    }
}
