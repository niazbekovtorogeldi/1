package com.example.finalproject.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.ZonedDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "stopList")
public class Stoplist {
    @Id
    @GeneratedValue(generator = "stopList_gen",strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "stopList_gen",sequenceName = "stopList_seq",allocationSize = 1)
    private Long id;
    private String reason;
    private ZonedDateTime date;


    @OneToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST})
    private MenuItem menuItem;
}
