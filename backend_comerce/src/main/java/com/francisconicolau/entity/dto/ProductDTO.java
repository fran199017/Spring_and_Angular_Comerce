package com.francisconicolau.entity.dto;


import com.fasterxml.jackson.annotation.JsonProperty;

public class ProductDTO {

    String name;

    String description;

    @JsonProperty("detalleProducto")
    DetalleProductoDTO detalleProductoDTO;

    int proveedorId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public DetalleProductoDTO getDetalleProductoDTO() {
        return detalleProductoDTO;
    }

    public void setDetalleProductoDTO(DetalleProductoDTO detalleProductoDTO) {
        this.detalleProductoDTO = detalleProductoDTO;
    }

    public int getProveedorId() {
        return proveedorId;
    }

    public void setProveedorId(int proveedorId) {
        this.proveedorId = proveedorId;
    }

    @Override
    public String toString() {
        return "ProductDTO{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", detalleProductoDTO=" + detalleProductoDTO +
                ", proveedorId=" + proveedorId +
                '}';
    }
}
