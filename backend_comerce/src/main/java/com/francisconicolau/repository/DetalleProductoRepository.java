package com.francisconicolau.repository;

import com.francisconicolau.entity.DetalleProducto;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository("detalleProductoRepository")
public interface DetalleProductoRepository extends PagingAndSortingRepository<DetalleProducto, Integer> {

}
