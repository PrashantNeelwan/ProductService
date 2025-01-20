package com.productservice.productservice.services;


import com.productservice.productservice.dtos.FakeStoreProductDto;
import com.productservice.productservice.dtos.GenericProductDto;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.ArrayList;

@Service("fakeStoreProductService")
public class FakeStoreProductService implements ProductService{

    // RestTemplate Builder will help RestTemplate object
    private RestTemplateBuilder restTemplateBuilder;

    //{ } +. anything inside {} is considered input from the user
    private String specificProductUrl = "https://fakestoreapi.com/products/{id}";

    private String genericProductUrl= "https://fakestoreapi.com/products";


    FakeStoreProductService(RestTemplateBuilder restTemplateBuilder){
        this.restTemplateBuilder = restTemplateBuilder;
    }

    private static GenericProductDto convertToGenericProductDto(FakeStoreProductDto fakeStoreProductDto)
    {
        GenericProductDto genericProductDto = new GenericProductDto();
        genericProductDto.setId(fakeStoreProductDto.getId());
        genericProductDto.setPrice(fakeStoreProductDto.getPrice());
        genericProductDto.setTitle(fakeStoreProductDto.getTitle());
        genericProductDto.setCategory(fakeStoreProductDto.getCategory());
        genericProductDto.setDescription(fakeStoreProductDto.getDescription());
        genericProductDto.setImage(fakeStoreProductDto.getImage());
        return genericProductDto;
    }

    @Override
    public GenericProductDto getProductById(Long id) {
        //Integrate the FakeStore API.
        //RestTemplate
        //RequestTemplate : helps you to make calls with the external system.
        // in Java we cant store json, so we need to store them in objects, so rest template will
        // convert the json from api to object and when we are sending data, object to json
        RestTemplate restTemplate = restTemplateBuilder.build();

//        ResponseEntity<String> responseEntity1 = restTemplate.getForEntity(getProductUrl, String.class);
//        System.out.println(responseEntity1.getBody());

        ResponseEntity<FakeStoreProductDto> responseEntity =
                restTemplate.getForEntity(specificProductUrl, FakeStoreProductDto.class, id);
     //   return responseEntity.getBody();


        // inspite of directly contact with FakeSotreProductDto, if api changes its attributes, we
        // we can one mare layet between FakeStoreProductService and Fakestore porduct Dto i.e GenericProductDto

        //convert FakeProductServieDto to GenericProductDto



        return convertToGenericProductDto(responseEntity.getBody());



    }

    @Override
    public List<GenericProductDto> getAllProducts() {
        RestTemplate restTemplate = restTemplateBuilder.build();
//        ResponseEntity<List<FakeStoreProductDto>> responseEntity=
//                restTemplate.getForEntity(getAllProductUrl, List<FakeStoreProductDto>.class)
        // this type of call must be working , but it does not.
        // List<FakeStoreProductDto>.class works because it gets converted in run time due
        // cause java is backward compatible

        // so we use arrays instead of list, because arrays do not have any concept of generics


        ResponseEntity<FakeStoreProductDto[]> responseEntity=
                restTemplate.getForEntity(genericProductUrl, FakeStoreProductDto[].class);
        List<GenericProductDto> result = new ArrayList<>();
        List<FakeStoreProductDto> fakeStoreProductDtos = List.of(responseEntity.getBody());
        for(FakeStoreProductDto fakeStoreProductDto : fakeStoreProductDtos)
        {
            result.add(convertToGenericProductDto(fakeStoreProductDto));
        }

        return result;
    }

    @Override
    public GenericProductDto createProduct(GenericProductDto genericProductDto) {
        RestTemplate restTemplate = restTemplateBuilder.build();

        ResponseEntity<FakeStoreProductDto> responseEntity =
                restTemplate.postForEntity(genericProductUrl, genericProductDto, FakeStoreProductDto.class);


        return convertToGenericProductDto(responseEntity.getBody());
    }

    @Override
    public void updateProductById() {

    }
}
