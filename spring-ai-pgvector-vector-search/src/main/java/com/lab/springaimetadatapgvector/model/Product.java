package com.lab.springaimetadatapgvector.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Product {


    private int id;
    @JsonProperty("product_name")
    private String productName;
    private String description;
    private String brand;
    private Double price;
    private String country;

}
