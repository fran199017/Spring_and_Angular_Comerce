import { Component, OnInit } from '@angular/core';
import { Router} from "@angular/router";
import { Producto } from '../productos';
import { DetalleProducto } from './detalleproducto';

@Component({
  selector: 'app-detalle',
  templateUrl: './detalle.component.html',
  styleUrls: ['./detalle.component.css']
})
export class DetalleComponent implements OnInit  {

  producto!: Producto;

  constructor(private router : Router) { }

  ngOnInit() {

  }


}
