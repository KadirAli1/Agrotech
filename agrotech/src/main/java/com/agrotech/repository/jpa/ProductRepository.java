package com.agrotech.repository.jpa;

import com.agrotech.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT p.specs FROM Product p WHERE p.productType.id = :id")
    public Collection<Product> findAllSpecsByProductType(@Param("id") Long id);

    public List<Product> findProductsByProductTypeId(Long id);

    public List<Product> findProductsByCountryId(Long id);

    public List<Product> findProductsByProductTypeIdAndCountryId(Long pId, Long cId);

    public List<Product> findByNameContaining(String query);

    public List<Product> findByProductTypeNameLike(String query);

    public List<Product> findByCountryNameLike(String query);
}
