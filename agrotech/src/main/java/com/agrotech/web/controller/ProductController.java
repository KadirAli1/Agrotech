package com.agrotech.web.controller;

import com.agrotech.config.layout.Layout;
import com.agrotech.model.Product;
import com.agrotech.service.CountryService;
import com.agrotech.service.ProductService;
import com.agrotech.service.ProductTypeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

@Controller
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @Autowired
    private ProductTypeService productTypeService;

    @Autowired
    private CountryService countryService;

    private Boolean compareError = false;

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Layout("layout/default")
    @GetMapping
    public String products(
            @RequestParam(value = "type", required = false) String type,
            @RequestParam(value = "country", required = false) String country,
            Model model) {

        List<Product> typeFilteredProducts = new ArrayList<>();
        List<Product> countryFilteredProducts = new ArrayList<>();

        if(type != null) {

            Long[] types = filterProductParameterHelper(type);

            for(int i=0; i<types.length; i++) {
                typeFilteredProducts.addAll( productService.findProductsByProductTypeId(types[i]) );
            }

            model.addAttribute("typeFilteredProducts", typeFilteredProducts);
        }

        if(country != null) {

            Long[] countries = filterProductParameterHelper(country);

            for(int i=0; i<countries.length; i++) {
                countryFilteredProducts.addAll( productService.findProductsByCountryId(countries[i]) );
            }

            model.addAttribute("countryFilteredProducts", countryFilteredProducts);
        }

        if(type != null && country != null) {

            Set<Product> bothFiltered = new HashSet<Product>();

            Long[] types = filterProductParameterHelper(type);
            Long[] countries = filterProductParameterHelper(country);

            for(int i=0; i<types.length; i++) {
                for(int j=0; j<countries.length; j++) {
                    bothFiltered.addAll(productService.findProductsByProductTypeIdAndCountryId(types[i], countries[j]));
                }
            }

            model.addAttribute("bothFiltered", bothFiltered);
        }

        if(type == null && country == null) {
            model.addAttribute("products", productService.findAll());
        }

        if(this.compareError) {
            model.addAttribute("compareError", "Selected products are not comparable. Please select products from the same product type!");
            this.compareError = false;
        }

        model.addAttribute("activeNav", "products");
        model.addAttribute("productTypes", productTypeService.findAll());
        model.addAttribute("countries", countryService.findAll());
        return "products";
    }

    private Long[] filterProductParameterHelper(String number) {

        String[] numbers = number.split(",");
        Long[] result = new Long[numbers.length];

        for(int i=0; i<numbers.length; i++) {
            result[i] = Long.parseLong(numbers[i]);
        }

        return result;
    }

    @Layout("layout/default")
    @GetMapping("/{id}")
    public String productDetails(@PathVariable("id") Long id, Model model) {

        Optional<Product> optProduct= productService.findById(id);

        Product noProduct= new Product();
        noProduct.setName("No product with id: " + id);

        model.addAttribute("activeNav", "products");
        model.addAttribute("productTypes", productTypeService.findAll());
        model.addAttribute("product", optProduct.orElse(noProduct));
        return "product_details";
    }

    @Layout("layout/default")
    @GetMapping("/compare/{firstId}/{secondId}")
    @Transactional
    public String compareProducts(@PathVariable("firstId") Long firstId, @PathVariable("secondId") Long secondId, Model model) {

        Product firstProduct = productService.findOne(firstId);
        Product secondProduct = productService.findOne(secondId);

        if(firstProduct.getProductType().getId() == secondProduct.getProductType().getId()) {
            model.addAttribute("activeNav", "products");
            model.addAttribute("productTypes", productTypeService.findAll());
            model.addAttribute("firstProduct", firstProduct);
            model.addAttribute("secondProduct", secondProduct);
            return "compare";
        }

        this.compareError = true;
        return "redirect:/products";
    }
}
