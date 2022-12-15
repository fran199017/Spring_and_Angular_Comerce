import { DetalleProducto } from "../detalleproducto/detalleproductos";

export class Producto {
  id!: number;
  name!: string;
  description!:string;
  detalleproducto!: DetalleProducto;

}
