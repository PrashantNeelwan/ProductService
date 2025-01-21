package com.productservice.productservice.controllers;

// whenever we are defining @Controller, @Service, @Repository, @Component then,
// we are saying to spring, to create an object of these classes at the time of initilisatoin


import com.productservice.productservice.dtos.ExceptionDto;
import com.productservice.productservice.dtos.FakeStoreProductDto;
import com.productservice.productservice.dtos.GenericProductDto;
import com.productservice.productservice.exceptions.ProductNotFoundException;
import com.productservice.productservice.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.rmi.server.ExportException;
import java.util.List;
import java.util.ArrayList;
@RestController         // why not @Controller, as this will tell spring that these will be REST API,s
@RequestMapping("/products")
public class ProductController {

    //private FakeStoreProductService fakeStoreProductService;  we should be creating reference of interface

    private ProductService productService;


    //@Qualifier are used when we have more than one objects that implements interface, it helps to know which one to use
    // @Autowired, will put the correct dependencies in the constructor
    // @ Autowired is optional in newer version of Spring , using autowired is field injection
    @Autowired
    //Constructor Injection
    ProductController(@Qualifier("fakeStoreProductService") ProductService productService){
        this.productService = productService;
    }

    //localhost:8080/products/12345
    @GetMapping("/{id}")
    public GenericProductDto getProductById(@PathVariable("id") Long id) throws ProductNotFoundException {
            //implementation
        // from the FakeStore ProductService call the getProductById() method
            return productService.getProductById(id);

    }

    @GetMapping
    public List<GenericProductDto> getAllProducts(){
        // should returns list of generic prodcutDtos

            return   productService.getAllProducts();
    }

    @DeleteMapping("/{id}")
    public GenericProductDto deleteProductById(@PathVariable("id") Long id){
        return productService.deleteProductById(id);
    }
    @PostMapping
    public GenericProductDto createProduct(@RequestBody GenericProductDto genericProductDto){
    //@RequestBody means that this object will come in the request in the body of the request
        // pic that body of request and map it to this object
        // we have something as request body for GET Method
        // ans response body for POST method
    // we also need to add id,
        return productService.createProduct(genericProductDto);

    }

    @PatchMapping("/{id}")
    public void updateProductById(@PathVariable("id") Long id){
    // you need request parameter {id} and response body as well.


        //return the updated complete object
    }


    // we need to create a method for every exception method created
//    @ExceptionHandler(ProductNotFoundException.class)
//        private ResponseEntity<ExceptionDto> handleProductNotFoundException (ProductNotFoundException productNotFoundException){
//        // this method automatically trigger when there is this exception
//        // its private
//        ExceptionDto exceptionDto = new ExceptionDto();
//        exceptionDto.setMessage(productNotFoundException.getMessage());
//        exceptionDto.setHttpStatus(HttpStatus .NOT_FOUND);
//
//        // changing the 202 OK to 404 not found
//        ResponseEntity responseEntity = new ResponseEntity(exceptionDto, HttpStatus.NOT_FOUND);
//
//        return responseEntity;
//
//    }
}


/*

3 ways of Dependency Injection: -

1. Constructor Injection (Most Recommended)
2. Field Injection (Not recommended, as it is not readable for new joinies)
3. Setter Injection
 */
