package com.agrotech.service.impl;

import com.agrotech.model.Product;
import com.agrotech.service.ProductService;
import com.agrotech.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public class StorageServiceImpl implements StorageService {
    @Autowired
    private ProductService productService;

    public void saveFile(Product product, MultipartFile file) {

        try {
            product.setPhotoData(file.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }

        productService.save(product);
    }
}
