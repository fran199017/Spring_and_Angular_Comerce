import { Component, OnInit } from '@angular/core';
import { Router} from "@angular/router";
import { Producto } from '../productos';
import { ProductosService } from 'src/app/shared/services/productos.service';

@Component({
  selector: 'app-detalle',
  templateUrl: './detalle.component.html',
  styleUrls: ['./detalle.component.css']
})
export class DetalleComponent implements OnInit  {

  productoId!: any;
  id !: number;
  producto !: Producto;

  constructor(
    private router : Router,
    private productoService : ProductosService,
    ) {
    this.productoId = this.router?.getCurrentNavigation()?.extras?.state
    this.id = this.productoId.id;

    this.chargeData();
   }

   ngOnInit(): void { }

   chargeData(){
    this.getProducto();
   }

  getProducto() {
    this.productoService.getProducto(this.id).subscribe(
      producto =>{
        this.producto = producto;
        console.log(this.producto);
      } 
    );   
  }


}
