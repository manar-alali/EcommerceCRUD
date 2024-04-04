package com.ecommerce.ecommercedemo.mappers;

public interface ProductMapper<A,B> {

    B mapTo(A a);

    A mapFrom(B b);

}