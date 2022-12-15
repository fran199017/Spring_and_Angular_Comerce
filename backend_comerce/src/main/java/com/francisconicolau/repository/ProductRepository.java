package com.francisconicolau.repository;

import com.francisconicolau.entity.Product;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;


@Repository("productRepository")
public interface ProductRepository extends PagingAndSortingRepository<Product, Integer> {

}
