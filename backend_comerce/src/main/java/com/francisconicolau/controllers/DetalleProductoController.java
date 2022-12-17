package com.francisconicolau.controllers;


import com.francisconicolau.entity.Descuento;
import com.francisconicolau.entity.DetalleProducto;
import com.francisconicolau.service.DescuentoService;
import com.francisconicolau.service.DetalleProductoService;
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
public class DetalleProductoController {

    private static final Logger log = LoggerFactory.getLogger(DetalleProductoController.class);

    private static final String DESCUENTO_CREADO ="Descuento creado";
    private static final String DESCUENTO_NO_ENCONTRADO ="Descuento no encontrado";

    private Map<String, Object> mapResponse = new HashMap<>();
    private DetalleProductoService detalleProductoService;

    @Autowired
    public DetalleProductoController(DetalleProductoService detalleProductoService) {
        this.detalleProductoService = detalleProductoService;
    }

    @PostMapping(value = "/detalleproducto/{id}/creardescuento")
    @ApiOperation(value = "Aplicar descuento a detalle de producto")
    public ResponseEntity<?> createDescuento(@PathVariable @ApiParam(value = "Detalleproducto's id", required = true) int id, int descuentoId) {
        try {
            var detalleProducto = detalleProductoService.crearDescuentoEnDetalle(id, descuentoId);
            if (detalleProducto != null){
                mapResponse.put("message",DESCUENTO_CREADO);
                mapResponse.put("response", detalleProducto);
                return new ResponseEntity<>(mapResponse, HttpStatus.OK);
            }
            mapResponse.put("message",DESCUENTO_NO_ENCONTRADO);
            mapResponse.put("response", HttpStatus.BAD_REQUEST);
            return new ResponseEntity<>(mapResponse, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            mapResponse.put("message", e.getMessage());
            mapResponse.put("response", HttpStatus.INTERNAL_SERVER_ERROR);
            return new ResponseEntity<>(mapResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
