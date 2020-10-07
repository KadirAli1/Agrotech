package com.agrotech.web.rest;


import com.agrotech.model.ProductType;
import com.agrotech.service.ProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.Collection;
import java.util.Optional;

@RestController
@CrossOrigin(
        value = "*",
        origins = "*",
        maxAge = 3600,
        allowCredentials = "true",
        allowedHeaders = "*",
        methods = RequestMethod.GET
)
@RequestMapping("/rest/product-types")
public class ProductTypeResource {

    @Autowired
    private ProductTypeService productTypeService;

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Collection<ProductType> getAllProductTypes() {
        return productTypeService.findAll();
    }

    @RequestMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ProductType getProductTypeById(@PathVariable("id") Long id) {
        Optional<ProductType> optionalProductType = productTypeService.findById(id);
        return optionalProductType.orElseThrow(() -> new EntityNotFoundException("No product type with id: " + id));
    }
}
