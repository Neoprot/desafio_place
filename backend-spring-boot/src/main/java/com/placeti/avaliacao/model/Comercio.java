package com.placeti.avaliacao.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.placeti.avaliacao.dto.CriaComercioDTO;

@Data
@NoArgsConstructor
@Entity
@Table(name = "comercio")
@AllArgsConstructor
public class Comercio {
    
    public Comercio(CriaComercioDTO dto, Cidade cidade) {
        this.id = dto.getId();
        this.nomeComercio = dto.getNomeComercio();
        this.nomeResponsavel = dto.getNomeResponsavel();
        this.tipoComercio = dto.getTipoComercio();
        this.cidade = cidade;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull(message = "nome do comercio não deve ser nulo")
    @Size(min = 1, max = 100, message = "tamanho do nome do comercio deve ser entre 1 e 100")
    @Column(name = "nome_comercio", nullable = false, length = 100)
    private String nomeComercio;

    @NotNull(message = "nome do responsavel não deve ser nulo")
    @Size(min = 1, max = 100, message = "tamanho do nome do nome do resposavel deve ser entre 1 e 100")
    @Column(name = "nome_responsavel", nullable = false, length = 100)
    private String nomeResponsavel;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Tipo do comercio não deve ser nulo")
    @Column(name = "tipo_comercio", nullable = false, length = 50)
    private TipoComercio tipoComercio;

    @ManyToOne
    @JoinColumn(name = "cidade_id", referencedColumnName = "id")
    @JsonBackReference
    private Cidade cidade;
}

