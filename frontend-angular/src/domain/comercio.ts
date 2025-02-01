import { Cidade } from "./cidade";
import { TipoComercio } from "./tipo-comercio.enum";

export class Comercio {
  id?: number;
  nomeComercio: string;
  nomeResponsavel: string;
  tipoComercio: TipoComercio;
  cidade: Cidade;
}
