package com.francisconicolau.service;


import com.francisconicolau.entity.Product;
import com.francisconicolau.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("productService")
public class ProductService {


    private ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    public Iterable<Product> getProducts() {
        Iterable<Product> products = productRepository.findAll();
        return products;

    }

    public Product getProductById(int id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()){
            return optionalProduct.get();
        }
        return null;
    }

    public void deleteProduct(int id) {
        Product product = getProductById(id);
        if (product != null){
            productRepository.delete(product);
        }

    }

    public void updateProduct(Product products) {
        productRepository.save(products);
    }

    public void createProduct(Product product) {
        productRepository.save(product);
    }
}
