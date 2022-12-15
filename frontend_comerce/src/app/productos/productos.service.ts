import { Injectable } from '@angular/core';
import { Producto } from './productos';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
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

}
