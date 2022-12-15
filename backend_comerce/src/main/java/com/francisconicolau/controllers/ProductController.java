package com.francisconicolau.controllers;


import com.francisconicolau.entity.Product;
import com.francisconicolau.service.ProductService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


@CrossOrigin(origins = { "http://localhost:4200" }) // Para permitir CORS en nuesta app angular.
@RequestMapping("/api/v1")
@RestController
public class ProductController {

    private static final Logger log = LoggerFactory.getLogger(ProductController.class);

    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(value = "/products")
    @ApiOperation(value = "Get all products")
    public ResponseEntity<?> getAllProducts() {
        try {
            var products = productService.getProducts();
            return new ResponseEntity<>(products, HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return null;
        }
    }

    @GetMapping(value = "/product/{id}")
    @ApiOperation(value = "Get one product")
    public ResponseEntity<?> getProduct(@PathVariable @ApiParam(value = "Game's id", required = true) int id) {
        try {
            var product = productService.getProductById(id);
            if (product != null){
                return new ResponseEntity<>(product, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return null;
        }
    }

    @GetMapping(value = "/product")
    public ModelAndView productsById(int id) {
        try {
            Product product = productService.getProductById(id);
            if (product != null){
                ModelAndView model = new ModelAndView();
                model.setViewName("product");
                model.addObject("product", product);
                return model;
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }


    @DeleteMapping(value = "/product")
    public ModelAndView deleteProduct(int id) {
        try {
            productService.deleteProduct(id);
            ModelAndView model = new ModelAndView();
            model.setViewName("index");
            return model;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

    @GetMapping(value = "/modifyproduct")
    public ModelAndView goUpdate(int id) {
        try {
            ModelAndView model = new ModelAndView();
            model.setViewName("modifyproduct");
            Product product = productService.getProductById(id);
            if (product != null){
                model.addObject("product", product);
                return model;
            }

        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

    @PostMapping(value = "/process_update")
    public ModelAndView processUpdate(Product product) {
        try {
            log.info("Product {}", product);
            productService.updateProduct(product);
            ModelAndView model = new ModelAndView("redirect:index");
            return model;

        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

    @GetMapping(value = "/process_create")
    public ModelAndView processCreateProduct() {
        try {
            ModelAndView model = new ModelAndView("process_create");
            model.addObject("product", new Product());
            return model;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

    @PostMapping(value = "/create_product")
    public ModelAndView createProduct(Product product) {
        try {
            log.info("Product {}", product);
            productService.createProduct(product);
            ModelAndView model = new ModelAndView("redirect:index");
            return model;

        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }
}
