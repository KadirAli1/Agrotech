package com.agrotech.service;

import com.agrotech.model.Product;

import java.util.Collection;
import java.util.List;

public interface ProductService  extends BaseEntityService<Product> {
    public Collection<Product> findAllSpecsByProductType(Long id);

    public List<Product> findProductsByProductTypeId(Long id);

    public List<Product> findProductsByCountryId(Long id);

    public List<Product> findProductsByProductTypeIdAndCountryId(Long pId, Long cId);

    public Long count();

    public List<Product> search(String query);
}
