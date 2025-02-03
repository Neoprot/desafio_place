package com.placeti.avaliacao.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
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
@AllArgsConstructor
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
    
    @NotNull(message = "nome não deve ser nulo")
    @Size(min = 1, max = 100, message = "tamanho do nome deve ser entre 1 e 100")
    @Column(name = "NOME", length = 100, nullable = false)
    private String nome;
    
    @NotNull(message = "uf não deve ser nulo")
    @Size(min = 1, max = 2, message = "tamanho do uf deve ser entre 1 e 2")
    @Column(name = "UF", length = 2, nullable = false)
    private String uf;
    
    @NotNull(message = "capital não deve ser nulo")
    @Column(name = "capital", nullable = false)
    private Boolean capital;

    @OneToMany(mappedBy = "cidade", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Comercio> comercios = new ArrayList<>();
}
