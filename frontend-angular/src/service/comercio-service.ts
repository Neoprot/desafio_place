import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { Comercio } from "@domain/comercio";
import { environment } from "../app/environments/environment";

@Injectable({
  providedIn: "root",
})
export class ComercioService {
  private baseUrl = `${environment.apiUrl}/comercios`;

  constructor(private http: HttpClient) {}
  listarComercios(): Observable<Comercio[]> {
    return this.http.get<Comercio[]>(this.baseUrl);
  }

  buscarComercio(id: number): Observable<Comercio> {
    return this.http.get<Comercio>(`${this.baseUrl}/${id}`);
  }

  criarComercio(comercio: Comercio): Observable<Comercio> {
    return this.http.post<Comercio>(this.baseUrl, comercio);
  }

  atualizarComercio(comercio: Comercio): Observable<Comercio> {
    return this.http.put<Comercio>(this.baseUrl, comercio);
  }

  excluirComercio(id: number): Observable<any> {
    return this.http.delete(`${this.baseUrl}/${id}`);
  }
}
