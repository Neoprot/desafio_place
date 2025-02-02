package com.placeti.avaliacao.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.placeti.avaliacao.dto.CriaCidadeDTO;

@Data
@NoArgsConstructor
@Entity
@Table(name = "cidade")
public class Cidade {

    public Cidade(CriaCidadeDTO dto) {
        this.id = dto.getId();
        this.nome = dto.getNome();
        this.uf = dto.getUf();
        this.capital = dto.getCapital();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "NOME", length = 100, nullable = false)
    private String nome;
    
    @Column(name = "UF", length = 100, nullable = false)
    private String uf;
    
    @Column(name = "capital", nullable = false)
    private Boolean capital;

    @OneToMany(mappedBy = "cidade", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Comercio> comercios = new ArrayList<>();
}
