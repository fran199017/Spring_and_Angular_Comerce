import { Injectable } from '@angular/core';
import { Producto } from '../../productos/productos';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { map } from 'rxjs/operators';


@Injectable()
export class ProductosService {
  private urlEndPoint: string = 'http://localhost:8080/api/v1/products';
  private urlDescuentoEndpoint: string = 'http://localhost:8080/api/v1/descuentos';
  private urlDetalleProducto: string = 'http://localhost:8080/api/v1/detalleproducto/';
  
  constructor(private http: HttpClient) { }

  getProductos(): Observable<Producto[]> {
    return this.http.get(this.urlEndPoint).pipe(
      map(response => response as Producto[])
    );
  }

  getProducto(id : number): Observable<Producto> {
    return this.http.get(this.urlEndPoint + "/" + id ).pipe(
      map(response => response as Producto)
    );
  }

  deleteProducto(id : number) : Observable<Map<string, object>> {
    return this.http.delete(this.urlEndPoint + "/" + id ).pipe(
      map(response => response as Map<string, object>)
    );
  }

  crearProducto(productData: any) : Observable<Map<string, object>> {
    const httpOptions = {
      headers: new HttpHeaders({'Content-Type': 'application/json'})
    }
    return this.http.post(this.urlEndPoint, productData, httpOptions).pipe(
      map(response => response as Map<string, object>)
    );
  }

  crearDescuento(porcentaje: number, nombre:string) : Observable<Map<string, object>> {
    const httpOptions = {
      headers: new HttpHeaders({'Content-Type': 'application/json'})
    }
    return this.http.post(this.urlDescuentoEndpoint + "?porcentaje=" + porcentaje + "&nombre=" + nombre, httpOptions).pipe(
      map(response => response as Map<string, object>)
    );
  }

  getDescuentos(): Observable<any> {
    return this.http.get(this.urlDescuentoEndpoint).pipe(
      map(response => response)
    );
  }

  aplicarDescuento(idDetalle : number, descuentoId : number): Observable<Map<string, object>> {
    const httpOptions = {
      headers: new HttpHeaders({'Content-Type': 'application/json'})
    }
    return this.http.post(this.urlDetalleProducto + idDetalle + "/creardescuento?descuentoId=" + descuentoId, httpOptions).pipe(
      map(response => response as Map<string, object>)
    );
  }

}
