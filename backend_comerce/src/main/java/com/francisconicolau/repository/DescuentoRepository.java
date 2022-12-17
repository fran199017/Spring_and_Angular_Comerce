package com.francisconicolau.repository;

import com.francisconicolau.entity.Descuento;
import com.francisconicolau.entity.DetalleProducto;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository("descuentoRepository")
public interface DescuentoRepository extends PagingAndSortingRepository<Descuento, Integer> {

}
