import { Component, OnInit } from '@angular/core';
import { Producto } from './productos';
import { ProductosService } from '../shared/services/productos.service';
import { Router, NavigationExtras} from "@angular/router";
import { ToastrService } from 'ngx-toastr';
import { FormGroup, FormControl, Validators, FormBuilder } from "@angular/forms";

@Component({
  selector: 'app-productos',
  templateUrl: './productos.component.html',
  styleUrls: ['./productos.component.css']
})
export class ProductosComponent implements OnInit {

  productos: Producto[] | undefined;
  responseMap : Map<string, object> = new Map<string, object>

  //Form de creacion de producto
  showForm : boolean = false;
  newProduct !: Producto;
  model = new Producto();
  productDataForm = new FormGroup({
    firstName: new FormControl("", [Validators.required]),
    lastName: new FormControl("", [Validators.required]),
  });

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

  addProduct(){
    if(this.showForm == false){
      this.showForm = true;
    }else{
      this.showForm = false;
    }
  }

    // //Form
    // onSubmit(form) {
    //   console.log("onSubmit");
    //   var productData : Producto = new Producto();

    //   productData.name = this.model.name;
    //   productData.description = this.model.description;
  
        
    //   console.log("Objeto partnerData");
    //   console.log(productData)
    //   // this.crearProducto(JSON.stringify(productData));
    //   console.log(form.value);
    // }

  
  get f() {
    return this.productDataForm.controls;
  }

  //Reload same URL.
  async reloadComponent() {
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
