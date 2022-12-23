import { Oferta } from "./oferta";

export class DetalleProducto {
  id!: number;
  material!: string;
  peso!:number;
  cost!:number;
  finalCost!: number;
  discount !: boolean;
  impuesto!: number;
  oferta !: Oferta;
}
