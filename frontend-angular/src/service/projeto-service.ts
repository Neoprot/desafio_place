import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Cidade } from "@domain/cidade";
import { Observable, from } from "rxjs";
import { environment } from "../app/environments/environment";

@Injectable({
  providedIn: "root",
})
export class ProjetoService {
  constructor(private http: HttpClient) {}
  private baseUrl = environment.apiUrl + environment.urlCidades;

  pesquisarCidades(): Observable<Cidade[]> {
    return this.http.get<Cidade[]>(this.baseUrl);
  }

  pesquisarCidadeporId(id: number): Observable<Cidade> {
    return this.http.get<Cidade>(`${this.baseUrl}/${id}`);
  }

  excluir(cidade: Cidade): Observable<any> {
    return this.http.delete(`${this.baseUrl}/${cidade.id}`);
  }

  salvar(cidade: Cidade): Observable<Cidade> {
    return this.http.post<Cidade>(this.baseUrl, cidade);
  }

  atualizarCidade(cidade: Cidade): Observable<Cidade> {
    return this.http.put<Cidade>(this.baseUrl, cidade);
  }
}
