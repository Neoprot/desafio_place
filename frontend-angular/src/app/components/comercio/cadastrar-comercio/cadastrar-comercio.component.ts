import { Component, EventEmitter, Input, Output } from "@angular/core";
import { Comercio } from "@domain/comercio";
import { ComercioService } from "@service/comercio-service";
import { MessageService } from "primeng/api";
import { ImportsModule } from "src/app/imports";
import { TipoComercio } from "@domain/tipo-comercio.enum";

@Component({
  selector: "app-cadastrar-comercio",
  templateUrl: "./cadastrar-comercio.component.html",
  imports: [ImportsModule],
  standalone: true,
  providers: [ComercioService, MessageService],
})
export class CadastrarComercioComponent {
  @Input() public cidadeId!: number;
  @Input() public comercio: Comercio = new Comercio();
  @Output() onClose: EventEmitter<boolean> = new EventEmitter();
  public displayDialog: boolean = true;

  tiposComercio = Object.values(TipoComercio);

  constructor(
    private comercioService: ComercioService,
    private messageService: MessageService
  ) {}

  salvar(): void {
    this.comercio.cidadeId = this.cidadeId;
    const request = this.comercioService.criarComercio(this.comercio);

    request.subscribe({
      next: (result) => {
        this.messageService.add({
          severity: "success",
          summary: "Sucesso",
          detail: "Comércio salvo com sucesso!",
        });
      },
      error: (err) => {
        console.error(err);
        this.messageService.add({
          severity: "error",
          summary: "Erro",
          detail: "Falha ao salvar o comércio.",
        });
      },
      complete: () => {
        this.displayDialog = false;
        this.onClose.emit(true);
      },
    });
  }

  cancelar(): void {
    this.displayDialog = false;
    this.onClose.emit(false);
  }
}
