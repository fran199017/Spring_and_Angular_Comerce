import { DetalleProducto } from "./detalle/detalleproducto";
import { Proveedor} from "./proveedor/proveedor"

export class Producto {
  id!: number;
  name!: string;
  description!:string;
  detalleproducto!: DetalleProducto;
  proveedor!: Proveedor

}
