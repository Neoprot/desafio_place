import { Component } from "@angular/core";
import { ImportsModule } from "./imports";
import { Cidade } from "@domain/cidade";
import { ProjetoService } from "@service/projeto-service";
import { CadastrarCidade } from "./cadastrar-cidade";
import { MessageService } from "primeng/api";
import { CadastrarComercioComponent } from "./components/comercio/cadastrar-comercio/cadastrar-comercio.component";

@Component({
  selector: "listar-cidades",
  templateUrl: "listar-cidades.html",
  standalone: true,
  imports: [ImportsModule, CadastrarCidade, CadastrarComercioComponent],
  providers: [ProjetoService, MessageService],
})
export class ListarCidades {
  listaCidades!: Cidade[];

  cidadeSelecionada: Cidade = new Cidade();

  mostraJanelaCadastro: boolean = false;
  mostraJanelaComercio: boolean = false;

  constructor(
    private service: ProjetoService,
    private messageService: MessageService
  ) {}

  ngOnInit() {
    this.pesquisarCidades();
  }

  private pesquisarCidades(): void {
    this.service.pesquisarCidades().subscribe((retorno) => {
      this.listaCidades = retorno;
    });
  }

  public abreJanelaParaCadastrarNovaCidade() {
    this.cidadeSelecionada = new Cidade();

    this.mostraJanelaCadastro = true;
  }

  public abreJanelaParaAlterarCidade(cidade: Cidade): void {
    this.cidadeSelecionada = new Cidade();
    this.cidadeSelecionada.id = cidade.id;
    this.cidadeSelecionada.nome = cidade.nome;
    this.cidadeSelecionada.uf = cidade.uf;
    this.cidadeSelecionada.capital = cidade.capital;

    this.mostraJanelaCadastro = true;
  }

  public excluir(cidade: Cidade) {
    this.service.excluir(cidade).subscribe((retorno) => {
      this.messageService.add({
        severity: "success",
        summary: "Info",
        detail: `Cidade '${cidade.nome}' excluída com sucesso!`,
      });

      setTimeout(() => this.pesquisarCidades(), 100);
    });
  }

  public fechaJanelaCadastro(salvou: boolean): void {
    this.mostraJanelaCadastro = false;

    if (salvou) {
      setTimeout(() => this.pesquisarCidades(), 100);
    }
  }

  public abreJanelaParaAdicionarComercio(cidade: Cidade): void {
    this.cidadeSelecionada = { ...cidade };
    this.mostraJanelaComercio = true;
    console.log("Cidade selecionada: ", this.cidadeSelecionada);
  }

  public fechaJanelaComercio(salvou: boolean): void {
    this.mostraJanelaComercio = false;
    if (salvou) {
      setTimeout(() => this.pesquisarCidades(), 100);
    }
  }
}
