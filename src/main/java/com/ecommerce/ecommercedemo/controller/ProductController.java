package com.ecommerce.ecommercedemo.controller;

import com.ecommerce.ecommercedemo.domain.dto.ProductDto;
import com.ecommerce.ecommercedemo.domain.entity.ProductEntity;
import com.ecommerce.ecommercedemo.mappers.ProductMapper;
import com.ecommerce.ecommercedemo.service.ProductService;
//import com.ecommerce.ecommercedemo.exception.ResourceNotFoundException;
//import com.ecommerce.ecommercedemo.domain.entity.ProductEntity;
//import com.ecommerce.ecommercedemo.repository.ProductRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.UUID;
//
//@RestController
//@RequestMapping("/api/v1")
//public class ProductController {
//
//    @Autowired
//    private ProductRepository productRepository;
//
//    @GetMapping("/products")
//    // TODO: use DTOs == Data Transfer Object
//    // TODO: need to use mapper class
//    // TODO: use service layer
//    public List<ProductEntity> getAllProducts() {
//        return productRepository.findAll();
//    }
//
//    @GetMapping("/products/{id}")
//    public ResponseEntity<ProductEntity> getProductById(@PathVariable(value = "id") UUID productId)
//            throws ResourceNotFoundException {
//        System.out.println(productId);
//        ProductEntity product = productRepository.findById(productId)
//                .orElseThrow(() -> new ResourceNotFoundException("Product not found for this id :: " + productId));
//        return ResponseEntity.ok().body(product);
//    }
//
//    @PostMapping("/products")
//    public ProductEntity createEmployee(@Valid @RequestBody ProductEntity product) {
//        return productRepository.save(product);
//    }
//
//    @PutMapping("/products/{id}")
//    public ResponseEntity<ProductEntity> updateEmployee(@PathVariable(value = "id") UUID productId,
//                                                   @Valid @RequestBody ProductEntity productDetails) throws ResourceNotFoundException {
//        ProductEntity product = productRepository.findById(productId)
//                .orElseThrow(() -> new ResourceNotFoundException("Product not found for this id :: " + productId));
//
//
//        product.setName(productDetails.getName());
//        product.setDescription(productDetails.getDescription());
//        product.setPrice(productDetails.getPrice());
//        final ProductEntity updatedProduct = productRepository.save(product);
//        return ResponseEntity.ok(updatedProduct);
//    }
//
//    @DeleteMapping("/products/{id}")
//    public Map<String, Boolean> deleteProduct(@PathVariable(value = "id") UUID productId)
//            throws ResourceNotFoundException {
//        ProductEntity product = productRepository.findById(productId)
//                .orElseThrow(() -> new ResourceNotFoundException("Product not found for this id :: " + productId));
//
//        productRepository.delete(product);
//        Map<String, Boolean> response = new HashMap<>();
//        response.put("deleted", Boolean.TRUE);
//        return response;
//    }
//}


    //get employees
//    @GetMapping
//    public List<ProductEntity> getAllProducts() {
//        return this.productRepository.findAll();
//    }
//
//    @GetMapping("/{id}")
//    public ProductEntity getProductById(@PathVariable UUID id) {
//        return productService.getProductById(id);
//    }
//
//    @PostMapping
//    public ProductEntity addProduct(@RequestBody ProductEntity product) {
//        return productService.addProduct(product);
//    }
//
//    @PutMapping("/{id}")
//    public ProductEntity updateProduct(@PathVariable UUID id, @RequestBody ProductEntity product) {
//        return productService.updateProduct(id, product);
//    }
//
//    @DeleteMapping("/{id}")
//    public void deleteProduct(@PathVariable UUID id) {
//        productService.deleteProduct(id);
//    }


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
public class ProductController {

    private ProductService productService;
    private ProductMapper<ProductEntity, ProductDto> productMapper;

    public ProductController(ProductMapper<ProductEntity, ProductDto> productMapper, ProductService productService) {
        this.productMapper = productMapper;
        this.productService = productService;
    }


    @PostMapping(path = "/products")
    public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto product) {

        // map from user then add uuid
        ProductEntity productEntity = productMapper.mapFrom(product);
        ProductEntity savedProductEntity = productService.save(productEntity);

        return new ResponseEntity<>(productMapper.mapTo(savedProductEntity), HttpStatus.CREATED);
    }
    @PutMapping(path = "/products/{id}")
    public ResponseEntity<ProductDto> createUpdateProduct(@PathVariable String uuid, @RequestBody ProductDto productDto) {
        ProductEntity productEntity = productMapper.mapFrom(productDto);
        boolean productExists = productService.isExists(uuid);
        ProductEntity savedProductEntity = productService.updateProduct(uuid, productEntity);
        ProductDto savedUpdatedProductDto = productMapper.mapTo(savedProductEntity);
        if (productExists) {
            return new ResponseEntity<>(savedUpdatedProductDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(savedUpdatedProductDto, HttpStatus.CREATED);
        }
    }

    @GetMapping("/products")
    public Page<ProductDto> listProducts(Pageable pageable) {
        Page<ProductEntity> products = productService.findAll(pageable);
        return products.map(productMapper::mapTo);
    }

    @GetMapping(path = "/products/{uuid}")
    public ResponseEntity<ProductDto> getProduct(@PathVariable("uuid") String uuid) {
        Optional<ProductEntity> foundProduct = productService.findOneByUuid(uuid);

        return foundProduct.map(productEntity -> {
            ProductDto productDto = productMapper.mapTo(productEntity);
            return new ResponseEntity<>(productDto, HttpStatus.OK);
        }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping(path = "/products/{uuid}")
    public ResponseEntity<Void> deleteProduct(@PathVariable("uuid") String uuid) {
        productService.deleteByUuid(uuid);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}

