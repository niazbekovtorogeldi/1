package com.example.finalproject.repository;
import com.example.finalproject.dto.dtoStopList.StopListResponse;
import com.example.finalproject.entity.Stoplist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
import java.util.Optional;

public interface StopLisRepository extends JpaRepository<Stoplist,Long> {
    @Query("select new com.example.finalproject.dto.dtoStopList.StopListResponse(s.id,s.reason,s.date)from Stoplist  s")
    List<StopListResponse> getAllStopList();

    Optional<StopListResponse> getStopListById(Long stopListId);
}
