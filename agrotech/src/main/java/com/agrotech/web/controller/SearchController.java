package com.agrotech.web.controller;

import com.agrotech.config.layout.Layout;
import com.agrotech.service.ProductService;
import com.agrotech.service.ProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/search")
public class SearchController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductTypeService productTypeService;

    @Layout("layout/default")
    @GetMapping
    public String search(@RequestParam("query") String query, Model model) {
        model.addAttribute("searchInfo", productService.search(query).size() + " products found for \"" + query + "\"");
        model.addAttribute("products", productService.search(query));
        model.addAttribute("productTypes", productTypeService.findAll());
        return "searchResults";
    }
}
