package com.productservice.productservice.models;

import lombok.Getter;
import lombok.Setter;

// lombok wll add all the methods of Getter and Setter for all the attributes
@Getter
@Setter

public class Product extends BaseModel{

    private Long id;
    private String title;
    private String desc;

    private int price;
    private String image;

    private Category category;
}
