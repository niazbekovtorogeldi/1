package com.example.finalproject.service;


import com.example.finalproject.dto.SimpleResponse.SimpleResponse;
import com.example.finalproject.dto.dtoCheque.AllCheque;
import com.example.finalproject.dto.dtoCheque.ChequePaginationResponse;
import com.example.finalproject.dto.dtoCheque.ChequeRequest;
import com.example.finalproject.dto.dtoCheque.ChequeResponse;

import java.util.List;


public interface ChequeService {
    List<ChequeResponse> getAllChequesByUserId(Long id);
    SimpleResponse saveCheque( Long userId,Long restoranId,ChequeRequest chequeRequest);
    ChequeResponse getChequeById(Long chequeId );
    SimpleResponse updateCheque(Long chequeId,ChequeRequest chequeRequest);
    SimpleResponse deletedId(Long chequeId);

    List<AllCheque> getFullInformationFromUser(Long id);
    Double getAllChecksSumsFromRestaurant(Long restaurantId);

    ChequePaginationResponse getPagination(Long id, int page, int size);


//    ChequePaginationResponse getPagination(Long id,int page, int size);
}
