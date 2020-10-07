package com.agrotech.web.rest;

import com.agrotech.model.Product;
import com.agrotech.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.io.IOException;
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
@RequestMapping("/rest/products")
public class ProductResource {

    @Autowired
    private ProductService productService;

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Collection<Product> getAllProducts() { return productService.findAll(); }

    @RequestMapping(method= RequestMethod.GET, value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Product getProductById(@PathVariable("id") Long id) {
        Optional<Product> optionalProduct = productService.findById(id);
        return optionalProduct.orElseThrow(() -> new EntityNotFoundException("No category with id: " + id));
    }

    @RequestMapping(value = "/type/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Collection<Product> getByProductType(@PathVariable("id") Long id) {
        return productService.findAllSpecsByProductType(id);
    }

    @Transactional
    @RequestMapping(value = "/image/{id}", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<byte[]> getImage(@PathVariable("id") Long id) throws IOException {

        Product product = productService.findOne(id);
        byte[] imageContent = product.getPhotoData();

        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);

        return new ResponseEntity<byte[]>(imageContent, headers, HttpStatus.OK);
    }
}
