import { DetalleProducto } from "./detalle/detalleproducto";

export class ProductoDTO {
  id!: number;
  name!: string;
  description!:string;
  detalleProducto!: DetalleProducto;
  proveedorId!: number

}
