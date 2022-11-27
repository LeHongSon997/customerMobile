package com.example.customermobile.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "vehicles")
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "type")
    private String type;

    @Column(name = "isSpecial")
    private boolean isSpecial;

    @Column(name = "code")
    private String code;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private String price;

    @Column(name = "pviCodeLx")
    private String pviCodeLx;

    @Column(name = "nameSearch")
    private String nameSearch;

    @Column(name = "parentCode")
    private String parentCode;
}
