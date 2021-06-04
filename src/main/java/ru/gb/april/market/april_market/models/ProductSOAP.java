package ru.gb.april.market.april_market.models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "productsoap")
@Data
public class ProductSOAP {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String name;

    @Column(name = "price")
    private Integer price;


}
