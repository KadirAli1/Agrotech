package com.agrotech.service;

import com.agrotech.model.ProductType;

import java.util.Collection;

public interface ProductTypeService extends BaseEntityService<ProductType> {
    public Collection<ProductType> findAll();

    public Long count();
}
