import { DetalleProducto } from "./detalle/detalleproductos";

export class Producto {
  id!: number;
  name!: string;
  description!:string;
  detalleproducto!: DetalleProducto;

}
