import { Injectable } from '@angular/core';
import { Proveedor } from 'src/app/productos/proveedor/proveedor';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { map, take } from 'rxjs/operators';


@Injectable()
export class ProveedoresService {
  private urlEndPoint: string = 'http://localhost:8080/api/v1/proveedores';
  
  constructor(private http: HttpClient) { }

  getProveedores(): Observable<Proveedor[]> {
    return this.http.get(this.urlEndPoint).pipe(
      map(response => response as Proveedor[])
    );
  }

  getProveedor(id : number): Observable<Proveedor> {
    return this.http.get(this.urlEndPoint + "/" + id ).pipe(
      map(response => response as Proveedor)
    );
  }


}
