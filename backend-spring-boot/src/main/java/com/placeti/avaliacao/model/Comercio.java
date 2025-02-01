package com.placeti.avaliacao.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Data
@NoArgsConstructor
@Entity
@Table(name = "comercio")
public class Comercio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "nome_comercio", nullable = false, length = 100)
    private String nomeComercio;

    @Column(name = "nome_responsavel", nullable = false, length = 100)
    private String nomeResponsavel;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_comercio", nullable = false, length = 50)
    private TipoComercio tipoComercio;

    @ManyToOne
    @JoinColumn(name = "cidade_id", referencedColumnName = "id")
    @JsonBackReference
    private Cidade cidade;
}
