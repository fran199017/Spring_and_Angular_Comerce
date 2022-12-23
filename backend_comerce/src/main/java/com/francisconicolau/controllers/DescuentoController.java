package com.francisconicolau.controllers;


import com.francisconicolau.service.DescuentoService;
import com.francisconicolau.service.ProveedorService;
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
public class DescuentoController {

    private static final Logger log = LoggerFactory.getLogger(DescuentoController.class);

    private static final String DESCUENTO_CREADO ="Descuento creado";

    private Map<String, Object> mapResponse = new HashMap<>();
    private DescuentoService descuentoService;

    @Autowired
    public DescuentoController(DescuentoService descuentoService) {
        this.descuentoService = descuentoService;
    }


    @PostMapping(value = "/descuentos")
    @ApiOperation(value = "Crear descuento")
    public ResponseEntity<?> createDescuento(int porcentaje, String nombre) {
        try {
            if (porcentaje <= 0){
                mapResponse.put("message","Descuento no puede ser 0");
                mapResponse.put("response", HttpStatus.BAD_REQUEST);
            }
            descuentoService.createDescuento(porcentaje, nombre);
            mapResponse.put("message", DESCUENTO_CREADO);
            mapResponse.put("response", HttpStatus.OK);
            return new ResponseEntity<>(mapResponse, HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return null;
        }
    }


    @GetMapping(value = "/descuentos")
    @ApiOperation(value = "Get all descuentos")
    public ResponseEntity<?> getAll() {
        try {
            return new ResponseEntity<>(descuentoService.getDescuentos(), HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return null;
        }
    }

    @GetMapping(value = "/descuentos/{id}")
    @ApiOperation(value = "Get descuento")
    public ResponseEntity<?> getDescuento(@PathVariable @ApiParam(value = "Descuento's id", required = true) int id) {
        try {
            var product = descuentoService.findById(id);
            if (product != null){
                return new ResponseEntity<>(product, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return null;
        }
    }
}
