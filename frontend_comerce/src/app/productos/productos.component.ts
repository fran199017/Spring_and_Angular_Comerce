import { Component, OnInit } from '@angular/core';
import { Producto } from './productos';
import { ProductosService } from '../shared/services/productos.service';
import { ProveedoresService } from '../shared/services/proveedores.service';
import { Router, NavigationExtras} from "@angular/router";
import { ToastrService } from 'ngx-toastr';
import { FormGroup, FormControl, ReactiveFormsModule, Validators } from "@angular/forms";
import { Proveedor } from './proveedor/proveedor';
import { ProductoDTO } from './productoDTO';
import { DetalleProducto } from './detalle/detalleproducto';

@Component({
  selector: 'app-productos',
  templateUrl: './productos.component.html',
  styleUrls: ['./productos.component.css']
})
export class ProductosComponent implements OnInit {

  constructor(
    private productosService: ProductosService, 
    private router : Router,
    private toast : ToastrService,
    private proveedorService : ProveedoresService
  ) {}

  productos: Producto[] | undefined;
  productoDTO !: ProductoDTO
  proveedores: Proveedor[] | undefined;
  
  responseMap : Map<string, object> = new Map<string, object>

  //Form de creacion de producto
  showForm : boolean = false;

  productDataForm = new FormGroup({
    name: new FormControl("Camiseta", [Validators.required]),
    description: new FormControl("Camiseta blanca con lunares", [Validators.required]),
    proveedor: new FormControl(2, [Validators.required]),
    // DetalleProducto
    material: new FormControl("Tela", [Validators.required]),
    peso: new FormControl("1.25", [Validators.required]),
    cost: new FormControl("15.99", [Validators.required]),
    impuesto: new FormControl("5", [Validators.required]),
  });



  async ngOnInit() {
    this.proveedorService.getProveedores().subscribe(
      proveedores => this.proveedores = proveedores
      );

    this.productosService.getProductos().subscribe(
      productos =>this.productos = productos
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
            this.reloadComponent();
          }
        }
      }
    );

  }

  addProduct(){
    if(this.showForm == false){
      this.showForm = true;
    }else{
      this.showForm = false;
    }
  }

  //Form
  onSubmit(form: any) {
    console.log("onSubmit");
    console.log(form)
    let productData : ProductoDTO = new ProductoDTO();
    let detalleProducto : DetalleProducto = new DetalleProducto();

    detalleProducto.cost = form.value.cost;
    detalleProducto.impuesto = form.value.impuesto;
    detalleProducto.material =  form.value.material;
    detalleProducto.peso =  form.value.peso;

    productData.name = form.value.name
    productData.description = form.value.description
    productData.detalleProducto = detalleProducto;
    productData.proveedorId = form.value.proveedor;
  
    console.log("Objeto partnerData");
    console.log(productData)
    this.productosService.crearProducto(JSON.stringify(productData)).subscribe(
      response =>{
        this.responseMap = response;
        console.log(this.responseMap)
        for (const [key, value] of Object.entries(this.responseMap)) {
          if(key == "message"){
            this.mostrarToast(value);
            this.reloadComponent();
          }
        }
      }
    );
    console.log(form.value);
  }

  onChangeSelect(proveedor:any){
    console.log(proveedor.target.value);
  }

  
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
