import { Component, OnInit, ViewChild } from '@angular/core';
import { Producto } from './productos';
import { ProductosService } from '../shared/services/productos.service';
import { ProveedoresService } from '../shared/services/proveedores.service';
import { Router, NavigationExtras} from "@angular/router";
import { ToastrService } from 'ngx-toastr';
import { FormGroup, FormControl, ReactiveFormsModule, Validators, NgForm } from "@angular/forms";
import { Proveedor } from './proveedor/proveedor';
import { ProductoDTO } from './productoDTO';
import { DetalleProducto } from './detalle/detalleproducto';

@Component({
  selector: 'app-productos',
  templateUrl: './productos.component.html',
  styleUrls: ['./productos.component.css']
})
export class ProductosComponent implements OnInit {

  @ViewChild('f') productForm !: NgForm;

  @ViewChild('fDescuento') descuentoForm !: NgForm;

  constructor(
    private productosService: ProductosService, 
    private router : Router,
    private toast : ToastrService,
    private proveedorService : ProveedoresService
  ) {}

  productos: Producto[] | undefined;
  productoDTO !: ProductoDTO
  proveedores: Proveedor[] | undefined;
  descuentos: any;

  materiales : string[] = ["Tela", "Seda", "Lino", "Pana", "Nylon", "Lycra", "Poliéster" ];
  impuestos : number[] = [0,5,10,21];
  
  responseMap : Map<string, object> = new Map<string, object>

  //Booleanos mostrar forms
  showForm : boolean = false;
  showFormDescuento : boolean = false;
  showTablaDescuentos : boolean = false;
  showDescuentos : boolean = false;

  async ngOnInit() {
    this.proveedorService.getProveedores().subscribe(
      proveedores => this.proveedores = proveedores
      );

    this.productosService.getProductos().subscribe(
      productos =>this.productos = productos
    );

    this.productosService.getDescuentos().subscribe(
      descuentos =>{
        this.descuentos = descuentos;
        if(this.descuentos != null){
          console.log("Hay descuentos");
          console.log(this.descuentos);
        }
      }
    );
  }

  verDescuentos(){
    if(this.showTablaDescuentos == false){
      this.showTablaDescuentos = true;
    }else{
      this.showTablaDescuentos = false;
    }
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

  crearDescuento(){
    if(this.showFormDescuento == false){
      this.showFormDescuento = true;
    }else{
      this.showFormDescuento = false;
    }
  }

  //Form
  onSubmit() {
    console.log("onSubmit");
    console.log(this.productForm)
    let productData : ProductoDTO = new ProductoDTO();
    let detalleProducto : DetalleProducto = new DetalleProducto();

    detalleProducto.cost = this.productForm.value.cost;
    detalleProducto.impuesto = this.productForm.value.impuesto;
    detalleProducto.material =  this.productForm.value.material;
    detalleProducto.peso =  this.productForm.value.peso;

    productData.name = this.productForm.value.name
    productData.description = this.productForm.value.description
    productData.detalleProducto = detalleProducto;
    productData.proveedorId = this.productForm.value.proveedor;
  
    console.log("Objeto partnerData");
    console.log(productData)
    this.productosService.crearProducto(JSON.stringify(productData)).subscribe(
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
  }

    //Form descuento
    onSubmitDescuento() {
      console.log("onSubmit");
      console.log(this.descuentoForm)

  
      var descuento : number = this.descuentoForm.value.descuento;
      var nombre : string = this.descuentoForm.value.name;
      console.log("El descuento a aplicar es de " + descuento)
      this.productosService.crearDescuento(descuento, nombre).subscribe(response =>{
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

  onChangeSelect(event:any){
    console.log(event.target.value);
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
