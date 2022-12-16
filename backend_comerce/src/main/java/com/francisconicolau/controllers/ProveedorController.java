package com.francisconicolau.controllers;


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
public class ProveedorController {

    private static final Logger log = LoggerFactory.getLogger(ProveedorController.class);

    private static final String DELETE_SUCCESFULL ="delete successfull";

    private Map<String, Object> mapResponse = new HashMap<>();
    private ProveedorService proveedorService;

    @Autowired
    public ProveedorController(ProveedorService proveedorService) {
        this.proveedorService = proveedorService;
    }

    @GetMapping(value = "/proveedores")
    @ApiOperation(value = "Get all proveedores")
    public ResponseEntity<?> getAll() {
        try {
            return new ResponseEntity<>(proveedorService.getProveedores(), HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return null;
        }
    }

    @GetMapping(value = "/proveedores/{id}")
    @ApiOperation(value = "Get proveedor")
    public ResponseEntity<?> getProveedor(@PathVariable @ApiParam(value = "Proveedor's id", required = true) int id) {
        try {
            var product = proveedorService.getProveedorById(id);
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
