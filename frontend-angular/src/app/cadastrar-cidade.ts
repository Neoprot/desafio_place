import { Component, EventEmitter, Input, Output } from "@angular/core";
import { ImportsModule } from "./imports";
import { Cidade } from "@domain/cidade";
import { ProjetoService } from "@service/projeto-service";
import { MessageService } from "primeng/api";

@Component({
  selector: "cadastrar-cidade",
  templateUrl: "cadastrar-cidade.html",
  standalone: true,
  imports: [ImportsModule],
  providers: [ProjetoService, MessageService],
})
export class CadastrarCidade {
  @Input() public cidade: Cidade = new Cidade();

  @Output("onClose") private eventoFechaJanela = new EventEmitter<boolean>();
  displayDialog: boolean = true;

  constructor(
    private service: ProjetoService,
    private messageService: MessageService
  ) {}

  public salvar(): void {
    this.service.salvar(this.cidade).subscribe({
      next: (result): void => {
        console.log(result);
        this.messageService.add({
          severity: "success",
          summary: "Info",
          detail: `Cidade '${this.cidade.nome}' cadastrada com sucesso!`,
        });
      },
      error: (error): void => {
        console.log(error);
        this.messageService.add({
          severity: "error",
          summary: "Erro",
          detail: `Cidade '${this.cidade.nome}' não foi salva!`,
        });
      },
      complete: (): void => {
        this.cancelar();
        this.reloadPage();
      },
    });
  }

  public cancelar(): void {
    this.eventoFechaJanela.emit(false);
  }

  public reloadPage(): void {
    setTimeout(() => window.location.reload(), 100);
  }
}
