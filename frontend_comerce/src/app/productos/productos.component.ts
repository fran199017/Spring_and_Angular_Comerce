import { Component, OnInit } from '@angular/core';
import { Producto } from './productos';
import { ProductosService } from '../shared/services/productos.service';
import { Router, NavigationExtras} from "@angular/router";
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-productos',
  templateUrl: './productos.component.html'
})
export class ProductosComponent implements OnInit {

  productos: Producto[] | undefined;
  responseMap : Map<string, object> = new Map<string, object>

  constructor(
      private productosService: ProductosService, 
      private router : Router,
      private toast : ToastrService,
    ) { }

  ngOnInit() {
    this.productosService.getProductos().subscribe(
      productos =>{
        this.productos = productos;
      } 
    );   
  }

  goProduct(id : number){
    let navigationExtras: NavigationExtras = {
      state: {
        id: id,
      }
    };

    this.router.navigate(["/detalle/1"], navigationExtras);
  }

  deleteProduct(id : number){
    this.productosService.deleteProducto(id).subscribe(
      response =>{
        this.responseMap = response;
        console.log(this.responseMap)
        for (const [key, value] of Object.entries(this.responseMap)) {
          if(key == "message"){
            this.mostrarToast(value);
          }
        }
      }
    );
    this.reloadComponent();
  }

  //Reload same URL.
  reloadComponent() {
    let currentUrl = this.router.url;
    this.router.routeReuseStrategy.shouldReuseRoute = () => false;
    this.router.onSameUrlNavigation = "reload";
    this.router.navigate([currentUrl]);
  }

  async mostrarToast(msg: string) {
    this.toast.info(msg, "Info" ,{
      timeOut :  3000
    })
  }

}
