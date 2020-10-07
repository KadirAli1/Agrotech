package com.agrotech.service;

import com.agrotech.model.Product;
import org.springframework.web.multipart.MultipartFile;

public interface StorageService {
    public void saveFile(Product product, MultipartFile file);
}
