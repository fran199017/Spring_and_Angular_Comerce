import { Injectable } from '@angular/core';
import { Producto } from '../../productos/productos';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { map } from 'rxjs/operators';


@Injectable()
export class ProductosService {
  private urlEndPoint: string = 'http://localhost:8080/api/v1/products';
  
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

}
