package com.ecommerce.ecommercedemo.mappers.impl;

import com.ecommerce.ecommercedemo.domain.dto.ProductDto;
import com.ecommerce.ecommercedemo.domain.entity.ProductEntity;
import com.ecommerce.ecommercedemo.mappers.ProductMapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;


@Component
public class ProductMapperImpl implements ProductMapper<ProductEntity, ProductDto> {

    private ModelMapper modelMapper;

    public ProductMapperImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public ProductDto mapTo(ProductEntity productEntity) {
        return modelMapper.map(productEntity, ProductDto.class);
    }

    @Override
    public ProductEntity mapFrom(ProductDto productDto) {
        return modelMapper.map(productDto, ProductEntity.class);
    }
}