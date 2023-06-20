package com.example.finalproject.entity;

import com.example.finalproject.enam.RestType;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "restaurants")
public class Restaurant {
    @Id
    @GeneratedValue(generator = "restaurant_gen ",strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "restaurant_gen",sequenceName = "restaurant_seq",allocationSize = 1)
    private Long id;
    private String name;
    private String location;
    private RestType restType;
    private int numberOfEmployees;
    private int service;
    @OneToMany(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST},mappedBy = "restaurant")
    private List<MenuItem> menuItem;
    @OneToMany(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST},mappedBy = "restaurant")
    private List<User>users;
}
