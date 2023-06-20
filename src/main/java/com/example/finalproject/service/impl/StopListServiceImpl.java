package com.example.finalproject.service.impl;

import com.example.finalproject.dto.SimpleResponse.SimpleResponse;
import com.example.finalproject.dto.dtoStopList.StopListRequest;
import com.example.finalproject.dto.dtoStopList.StopListResponse;
import com.example.finalproject.entity.MenuItem;
import com.example.finalproject.entity.Stoplist;
import com.example.finalproject.repository.MenuItemRepository;
import com.example.finalproject.repository.StopLisRepository;
import com.example.finalproject.service.StopListService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class StopListServiceImpl implements StopListService {
    private final StopLisRepository stopLisRepository;
    private final MenuItemRepository menuItemRepository;
    @Override
    public List<StopListResponse> getAllStopList() {
        return stopLisRepository.getAllStopList();
    }

    @Override
    public SimpleResponse saveStopList(Long menuItemId, StopListRequest stopListRequest) {
        MenuItem menuItem1 = menuItemRepository.findById(menuItemId).orElseThrow(()->new NullPointerException(" in menuItem no such id"));
        Stoplist stoplist= new Stoplist();
        stoplist.setReason(stopListRequest.reason());
        stoplist.setDate(stopListRequest.date());
        List<Stoplist> stoplists= new ArrayList<>();
        stoplists.add(stoplist);
        stoplist.setMenuItem(menuItem1);
        menuItem1.setStoplist(stoplist);
        stopLisRepository.save(stoplist);
        menuItemRepository.save(menuItem1);
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message(String.format("save StopList "))
                .build();
    }

    @Override
    public StopListResponse getStopListById(Long stopListId) {
        return stopLisRepository.getStopListById(stopListId).orElseThrow(()->new NullPointerException(""));
    }

    @Override
    public SimpleResponse updateStopList(Long id, StopListRequest stopListRequest) {
        Stoplist stoplist= stopLisRepository.findById(id).orElseThrow(()-> new NullPointerException(""));
        stoplist.setDate(stopListRequest.date());
        stoplist.setReason(stopListRequest.reason());
        stopLisRepository.save(stoplist);
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message(String.format("updated"))
                .build();
    }

    @Override
    public SimpleResponse deleteStopListById(Long id) {
        Stoplist stoplist = stopLisRepository.findById(id).orElseThrow(()->new NullPointerException(""));
        stopLisRepository.delete(stoplist);
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message(String.format("deleted"+id))
                .build();
    }
}
