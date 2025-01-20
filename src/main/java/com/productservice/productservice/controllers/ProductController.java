package com.productservice.productservice.controllers;

// whenever we are defining @Controller, @Service, @Repository, @Component then,
// we are saying to spring, to create an object of these classes at the time of initilisatoin


import com.productservice.productservice.dtos.FakeStoreProductDto;
import com.productservice.productservice.dtos.GenericProductDto;
import com.productservice.productservice.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

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
    public GenericProductDto getProductById(@PathVariable("id") Long id){
            //implementation
        // from the FakeStore ProductService call the getProductById() method
            return productService.getProductById(id);

    }

    @GetMapping
    public void getAllProducts(){


    }

    @DeleteMapping("/{id}")
    public void deleteProductById(){


    }

    public void createProduct(){


    }


    public void updateProductById(){


    }


}


/*

3 ways of Dependency Injection: -

1. Constructor Injection (Most Recommended)
2. Field Injection (Not recommended, as it is not readable for new joinies)
3. Setter Injection
 */
