package com.francisconicolau.service;


import com.francisconicolau.entity.Product;
import com.francisconicolau.entity.dto.ProductDTO;
import com.francisconicolau.repository.ProductRepository;
import com.francisconicolau.repository.ProveedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("productService")
public class ProductService {


    private ProductRepository productRepository;
    private DetalleProductoService detalleProductoService;
    private ProveedorService proveedorService;

    @Autowired
    public ProductService(ProductRepository productRepository, DetalleProductoService detalleProductoService, ProveedorService proveedorService) {
        this.productRepository = productRepository;
        this.detalleProductoService = detalleProductoService;
        this.proveedorService = proveedorService;
    }


    public Iterable<Product> getProducts() {
        return productRepository.findAll();
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

    public Product createProduct(ProductDTO productDTO) {
        Product product = new Product();

        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());

        var detalleProductoDTO = productDTO.getDetalleProductoDTO();
        var detalleProducto = detalleProductoService.createDetalleProducto(detalleProductoDTO);

        var proveedorId = productDTO.getProveedorId();
        var proveedor = proveedorService.getProveedorById(proveedorId);
        product.setDetalleProducto(detalleProducto);
        product.setProveedor(proveedor);

        return productRepository.save(product);
    }
}
