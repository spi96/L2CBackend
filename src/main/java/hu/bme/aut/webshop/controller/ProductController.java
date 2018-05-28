package hu.bme.aut.webshop.controller;

import hu.bme.aut.webshop.dao.ProductRepository;
import hu.bme.aut.webshop.domain.Product;
import hu.bme.aut.webshop.domain.enumeration.Brand;
import hu.bme.aut.webshop.domain.enumeration.ProductCategory;
import hu.bme.aut.webshop.domain.enumeration.ProductSize;
import hu.bme.aut.webshop.domain.enumeration.ProductType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.EnumSet;
import java.util.Map;

@Controller
public class ProductController {

    @Autowired
    ProductRepository productRepository;

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String listProducts(Map<String, Object> map) {
        map.put("sizes", EnumSet.allOf(ProductSize.class));
        map.put("productList", productRepository.findAll());
        return "home";
    }

    @RequestMapping(value = "/addItem", method = RequestMethod.POST)
    @PreAuthorize("hasRole('ADMIN')")
    public String addItem(
            @ModelAttribute("newProduct") Product newProduct) {

        productRepository.save(newProduct);
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

        productRepository.deleteById(Long.parseLong(productId));
        return "redirect:home";
    }
}
