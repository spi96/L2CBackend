package com.example.spi.localize2socialize.controller;

import com.example.spi.localize2socialize.domain.Product;
import com.example.spi.localize2socialize.domain.enumeration.Brand;
import com.example.spi.localize2socialize.domain.enumeration.ProductCategory;
import com.example.spi.localize2socialize.domain.enumeration.ProductSize;
import com.example.spi.localize2socialize.domain.enumeration.ProductType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.EnumSet;
import java.util.Map;

@Controller
public class ProductController {


    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String listProducts(Map<String, Object> map) {
        map.put("sizes", EnumSet.allOf(ProductSize.class));
        return "home";
    }

    @RequestMapping(value = "/addItem", method = RequestMethod.POST)
    @PreAuthorize("hasRole('ADMIN')")
    public String addItem(
            @ModelAttribute("newProduct") Product newProduct) {


        return "redirect:addItem";
    }

    @RequestMapping(value = "/addItem", method = RequestMethod.GET)
    public String addItemView(Map<String, Object> map) {
        map.putIfAbsent("newProduct", new Product());
        map.put("brands", EnumSet.allOf(Brand.class));
        map.put("categories", EnumSet.allOf(ProductCategory.class));
        map.put("types", EnumSet.allOf(ProductType.class));

        return "addItem";
    }

    @RequestMapping(value = "/deleteProduct", method = RequestMethod.GET)
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteProduct(
            @RequestParam(name="productId")String productId) {

        return "redirect:home";
    }
}
