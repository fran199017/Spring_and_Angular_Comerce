package com.francisconicolau.controllers;


import com.francisconicolau.entity.Product;
import com.francisconicolau.entity.dto.ProductDTO;
import com.francisconicolau.service.ProductService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@CrossOrigin(origins = { "http://localhost:4200" }) // Para permitir CORS en nuesta app angular.
@RequestMapping("/api/v1")
@RestController
public class ProductController {

    private static final Logger log = LoggerFactory.getLogger(ProductController.class);

    private static final String DELETE_SUCCESFULL ="delete successfull";
    private static final String PRODUCT_CREATED ="Product created";

    private Map<String, Object> mapResponse = new HashMap<>();
    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping(value = "/products")
    @ApiOperation(value = "Create product")
    public ResponseEntity<?> createProduct(@RequestBody ProductDTO productDTO) {
        try {
            var product = productService.createProduct(productDTO);

            mapResponse.put("message",PRODUCT_CREATED);
            mapResponse.put("response", product);

            return new ResponseEntity<>(mapResponse, HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            mapResponse.put("message", e.getMessage());
            mapResponse.put("response", HttpStatus.INTERNAL_SERVER_ERROR);
            return new ResponseEntity<>(mapResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
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

    @GetMapping(value = "/products/{id}")
    @ApiOperation(value = "Get one product")
    public ResponseEntity<?> getProduct(@PathVariable @ApiParam(value = "Product's id", required = true) int id) {
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

    @DeleteMapping(value = "/products/{id}")
    @ApiOperation(value = "Delete a product")
    public ResponseEntity<?> deleteProducto(@PathVariable @ApiParam(value = "Product's id", required = true) int id) {
        try {
             productService.deleteProduct(id);
             mapResponse.put("response", HttpStatus.OK);
             mapResponse.put("message", DELETE_SUCCESFULL);
             return new ResponseEntity<>(mapResponse,HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return null;
        }
    }
}
