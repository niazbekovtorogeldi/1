package com.example.finalproject.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "menuItems")
public class MenuItem {
    @Id
    @GeneratedValue(generator = "menuItem_gen",strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "menuItem_gen",sequenceName = "menuItem_seq",allocationSize = 1)

    private Long id;
    private String name;
    @Lob
    private List<String>image;
    private int price;
    private String description;
    private boolean isVegetarian;


    @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE})
    private Subcategory subcategory;

    @OneToOne(cascade = {CascadeType.ALL},mappedBy = "menuItem")
    private Stoplist stoplist;

    @ManyToMany(cascade = {CascadeType.ALL},mappedBy = "menuItems")
    private List<Cheque>cheques;

    @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH})
    private Restaurant restaurant;
}
