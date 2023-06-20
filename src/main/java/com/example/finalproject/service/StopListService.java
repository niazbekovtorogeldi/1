package com.example.finalproject.service;

import com.example.finalproject.dto.SimpleResponse.SimpleResponse;
import com.example.finalproject.dto.dtoStopList.StopListRequest;
import com.example.finalproject.dto.dtoStopList.StopListResponse;

import java.util.List;

public interface StopListService {
    List<StopListResponse> getAllStopList();
    SimpleResponse saveStopList(Long menuItem, StopListRequest stopListRequest);
    StopListResponse getStopListById(Long stopListId);
    SimpleResponse updateStopList(Long id,StopListRequest stopListRequest);
    SimpleResponse deleteStopListById(Long id);


}
