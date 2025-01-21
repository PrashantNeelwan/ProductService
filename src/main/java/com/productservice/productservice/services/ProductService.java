package com.productservice.productservice.services;


import com.productservice.productservice.dtos.FakeStoreProductDto;
import com.productservice.productservice.dtos.GenericProductDto;

import java.util.List;
//why product Service should be an interface and not a class
//because : in projectController we will need a object of Product service
// so first if two classes are dependent on one object : Design Principle of Dependency Inversion is violated
// and second : as an interface, we can have multiple implementation of ProductService
// OOPs LLD : "Code to interface rather than implementation"
public interface ProductService {

    // by default all the methods of interface are public
    // we can not create objects for interface
    GenericProductDto getProductById(Long id);

    List<GenericProductDto> getAllProducts();

    GenericProductDto createProduct(GenericProductDto genericProductDto);

    GenericProductDto deleteProductById(Long id);
    void updateProductById();


}
