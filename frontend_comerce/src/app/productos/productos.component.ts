import { Component, OnInit } from '@angular/core';
import { Producto } from './productos';
import { ProductosService } from './productos.service';
import { Router, NavigationExtras} from "@angular/router";

@Component({
  selector: 'app-productos',
  templateUrl: './productos.component.html'
})
export class ProductosComponent implements OnInit {

  productos: Producto[] | undefined;

  constructor(private productosService: ProductosService, private router : Router) { }

  ngOnInit() {
    this.productosService.getProductos().subscribe(
      productos =>{
        this.productos = productos;
        console.log(this.productos);
      } 
    );   
  }

  goProduct(producto : Producto){
    console.log(producto);
    let navigationExtras: NavigationExtras = {
      state: {
        producto: producto,
      },
      queryParams: {},
    };

    this.router.navigate(["/detalle/" + producto.id], navigationExtras);
  }

}
