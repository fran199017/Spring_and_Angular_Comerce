package com.francisconicolau.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

@Entity
@Table(name = "product")
@JsonSerialize()
public class Product {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)  //To add prices h2 database
    private int id;

    private String name;

    private String description;

    @OneToOne
    @JoinColumn(name = "detalle_producto_id")
    private DetalleProducto detalleProducto;

    public DetalleProducto getDetalleProducto() {
        return detalleProducto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public void setDetalleProducto(DetalleProducto detalleProducto) {
        this.detalleProducto = detalleProducto;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", detalleProducto=" + detalleProducto +
                '}';
    }
}
