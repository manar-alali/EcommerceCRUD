package com.ecommerce.ecommercedemo.service.impl;


import com.ecommerce.ecommercedemo.domain.entity.ProductEntity;
import com.ecommerce.ecommercedemo.repository.ProductRepository;
import com.ecommerce.ecommercedemo.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ProductEntity updateProduct(String id, ProductEntity product) {
        return productRepository.save(product);
    }

    @Override
    public ProductEntity updateByUuid(String uuid, ProductEntity product) {
        return productRepository.save(product);
    }

    @Override
    public List<ProductEntity> findAll() {
        return StreamSupport
                .stream(
                        productRepository.findAll().spliterator(),
                        false)
                .collect(Collectors.toList());
    }

    @Override
    public Page<ProductEntity> findAll(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Override
    public Optional<ProductEntity> findOne(String id) {
        return productRepository.findById(id);
    }

    @Override
    public Optional<ProductEntity> findOneByUuid(String uuid) {
        return productRepository.findByUuid(uuid);
    }

    @Override
    public boolean isExists(String id) {
        return productRepository.existsById(id);
    }

    @Override
    public void delete(String id) {
        productRepository.deleteById(id);
    }

    @Override
    public void deleteByUuid(String uuid) {
        productRepository.deleteByUuid(uuid);

    }

    @Override
    public ProductEntity save(ProductEntity productEntity) {

        if (productEntity.getUuid() == null){
            productEntity.setUuid(UUID.randomUUID().toString());
        }

        return productRepository.save(productEntity);
    }

}