package com.example.finalproject.service.impl;

import com.example.finalproject.dto.SimpleResponse.SimpleResponse;
import com.example.finalproject.dto.dtoCheque.AllCheque;
import com.example.finalproject.dto.dtoCheque.ChequePaginationResponse;
import com.example.finalproject.dto.dtoCheque.ChequeRequest;
import com.example.finalproject.dto.dtoCheque.ChequeResponse;
import com.example.finalproject.entity.Cheque;
import com.example.finalproject.entity.MenuItem;
import com.example.finalproject.entity.User;
import com.example.finalproject.repository.ChequeRepository;
import com.example.finalproject.repository.MenuItemRepository;
import com.example.finalproject.repository.UserRepository;
import com.example.finalproject.service.ChequeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import java.util.ArrayList;
import java.util.List;
@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class ChequeServiceImpl implements ChequeService {
    private final ChequeRepository chequeRepository;
    private final UserRepository userRepository;
    private final MenuItemRepository menuItemRepository;

    @Override
    public List<ChequeResponse> getAllChequesByUserId(Long id) {
        return chequeRepository.getAllUSerId(id);
    }


    @Override
    public SimpleResponse saveCheque(Long userId, Long menuId, ChequeRequest chequeRequest) {
        User user = userRepository.findById(userId).orElseThrow(() -> new NullPointerException(""));
        MenuItem menuItem = menuItemRepository.findById(menuId).orElseThrow(() -> new NullPointerException(""));
        Cheque cheque = new Cheque();
        cheque.setPriceAverage(chequeRequest.priceAverage());
        List<Cheque> cheques = new ArrayList<>();
        List<MenuItem> menuItems = new ArrayList<>();
        menuItems.add(menuItem);
        cheques.add(cheque);
        cheque.setUser(user);
        user.setCheques(cheques);
        menuItem.setCheques(cheques);
        cheque.setMenuItems(menuItems);

        chequeRepository.save(cheque);
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("save")
                .build();
    }

    @Override
    public ChequeResponse getChequeById(Long chequeId) {
        return chequeRepository.findAllById(chequeId).orElseThrow(() -> new NullPointerException(""));


    }

    @Override
    public SimpleResponse updateCheque(Long chequeId, ChequeRequest chequeRequest) {

        Cheque cheque = chequeRepository.findById(chequeId).orElseThrow(() -> new NullPointerException(""));
        cheque.setPriceAverage(chequeRequest.priceAverage());
        chequeRepository.save(cheque);
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("")
                .build();
    }

    @Override
    public SimpleResponse deletedId(Long chequeId) {
        Cheque cheque = chequeRepository.findById(chequeId).orElseThrow(() -> new NullPointerException(""));
        chequeRepository.delete(cheque);
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("")
                .build();
    }
    @Override
    public List<AllCheque> getFullInformationFromUser(Long id) {
        Double service = chequeRepository.grandTotal(id) * 12 / 100;
        List<AllCheque> allChequeResponses = new ArrayList<>();
        AllCheque allChequeResponse = AllCheque.builder()
                .fullName(chequeRepository.userFullName(id))
                .items(chequeRepository.getAllMenuItems(id))
                .price(chequeRepository.averagePrice(id))
                .Service(service)
                .GrandTotal(chequeRepository.grandTotal(id))
                .build();
        allChequeResponses.add(allChequeResponse);
        return allChequeResponses;
    }

    @Override
    public Double getAllChecksSumsFromRestaurant(Long restaurantId) {
        return chequeRepository.getAllChecksSumsFromRestaurant(restaurantId);
    }

    @Override
    public ChequePaginationResponse getPagination(Long id, int page, int size) {
        Pageable pageable = PageRequest.of(page-1, size);
        Page<ChequeResponse>chequeResponses = chequeRepository.getAllMenuItems(id,pageable);
        return ChequePaginationResponse.builder()
                .menuItemResponseList(chequeResponses.getContent())
                .page(chequeResponses.getNumber()+1)
                .size(chequeResponses.getTotalPages())
                .build();
    }

}


