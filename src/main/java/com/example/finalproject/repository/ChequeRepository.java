package com.example.finalproject.repository;



import com.example.finalproject.dto.dtoCheque.ChequeResponse;
import com.example.finalproject.dto.dtoMenultem.MenuItemResponse;
import com.example.finalproject.entity.Cheque;
import org.springframework.data.domain.Page;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;

public interface ChequeRepository extends JpaRepository<Cheque, Long> {
    @Query("select new com.example.finalproject.dto.dtoCheque.ChequeResponse(c.id,c.priceAverage,c.createAt) from Cheque c where c.user.id=:id")
    List<ChequeResponse> getAllUSerId(Long id);

    Optional<ChequeResponse> findAllById(Long chequeId);

    @Query("select new com.example.finalproject.dto.dtoMenultem.MenuItemResponse" +
            "(m.id, m.name, m.image, m.price, m.description, m.isVegetarian)" +
            " from MenuItem m join m.cheques mc where mc.user.id = :id")
    Page<ChequeResponse> getAllMenuItems(Long id, Pageable pageable);
    @Query("select new com.example.finalproject.dto.dtoMenultem.MenuItemResponse" +
            "(m.id, m.name, m.image, m.price, m.description, m.isVegetarian)" +
            " from MenuItem m join m.cheques mc where mc.user.id = :id")
    List<MenuItemResponse> getAllMenuItems(Long id);

    @Query("select avg(m.price) from MenuItem m join m.cheques mc where mc.user.id = :id")
    Double averagePrice(Long id);


    @Query("select sum(m.price) from MenuItem m join m.cheques mc where mc.user.id = :id")
    Double grandTotal(Long id);


    @Query("select sum (m.price)from MenuItem  m where m.restaurant.id=:restaurantId")
    Double getAllChecksSumsFromRestaurant(Long restaurantId);
    @Query("select concat(u.firstName,' ' ,u.lastName) as fullname from User  u where u.id=:id ")
    String userFullName(Long id);
}
