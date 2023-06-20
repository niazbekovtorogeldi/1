package com.example.finalproject.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "subcategories")
public class Subcategory {
    @Id
    @GeneratedValue(generator = "subcategory_gen", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "subcategory_gen", sequenceName = "subcategory_seq", allocationSize = 1)
    private Long id;
    private String name;

    @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST})
    private Category category;
    @OneToMany(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST},mappedBy = "subcategory")
    private List<MenuItem>menuItems;
}
