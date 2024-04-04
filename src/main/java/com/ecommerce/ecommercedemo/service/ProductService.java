package com.ecommerce.ecommercedemo.service;

//import com.ecommerce.ecommercedemo.model.ProductEntity;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//
//import java.util.List;
//import java.util.Optional;
//
//public interface ProductService {
//
//    ProductEntity createUpdateProduct(Long id, ProductEntity product);
//
//    List<ProductEntity> findAll();
//
//    Page<ProductEntity> findAll(Pageable pageable);
//
//    Optional<ProductEntity> findOne(Long id);
//
//    boolean isExists(Long id);
//
//    void delete(Long id);
//
//}

import com.ecommerce.ecommercedemo.domain.entity.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    ProductEntity updateProduct(String id, ProductEntity product);

    ProductEntity updateByUuid(String uuid, ProductEntity product);


    List<ProductEntity> findAll();

    Page<ProductEntity> findAll(Pageable pageable);

    Optional<ProductEntity> findOne(String id);

    Optional<ProductEntity> findOneByUuid(String uuid);

    boolean isExists(String id);

    void delete(String id);

    void deleteByUuid(String uuid);

    ProductEntity save(ProductEntity productEntity);

}