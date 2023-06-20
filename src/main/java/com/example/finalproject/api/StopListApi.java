package com.example.finalproject.api;

import com.example.finalproject.dto.SimpleResponse.SimpleResponse;
import com.example.finalproject.dto.dtoStopList.StopListRequest;
import com.example.finalproject.dto.dtoStopList.StopListResponse;
import com.example.finalproject.service.StopListService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/stopLists")
@RequiredArgsConstructor
public class StopListApi {
    private final StopListService service;

    @GetMapping
    public List<StopListResponse> getAllStopList() {
        return service.getAllStopList();
    }
    @PreAuthorize("hasAnyAuthority('ADMIN','CHEF')")
    @PostMapping("/{id}")
    public SimpleResponse saveStopList(@PathVariable Long id,
                                       @RequestBody StopListRequest stopListRequest) {
        return service.saveStopList(id, stopListRequest);
    }

    @GetMapping("/{id}")
    public StopListResponse getStopListById(@PathVariable Long id) {
        return service.getStopListById(id);
    }
    @PreAuthorize("hasAnyAuthority('ADMIN','CHEF')")
    @PutMapping("/{id}")
    public SimpleResponse updateStopListById(@PathVariable Long id,
                                             @RequestBody StopListRequest stopListRequest) {
        return service.updateStopList(id, stopListRequest);
    }

    @DeleteMapping("/{id}")
    public SimpleResponse deleteStopListById(@PathVariable Long id) {
        return service.deleteStopListById(id);

    }
}
