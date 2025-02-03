package com.placeti.avaliacao.dto;

import java.util.stream.Collectors;

import com.placeti.avaliacao.model.Cidade;
import com.placeti.avaliacao.model.Comercio;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CriaCidadeDTO {
    private Long id;
    private String nome;
    private String uf;
    private Boolean capital;
    private String nomesComercios;
    
    
    public static CriaCidadeDTO toDTO(Cidade cidade) {
        if(cidade == null) {
            return null;
        }
        CriaCidadeDTO dto = new CriaCidadeDTO();
        dto.setId(cidade.getId());
        dto.setNome(cidade.getNome());
        dto.setUf(cidade.getUf());
        dto.setCapital(cidade.getCapital());
        String nomes = cidade.getComercios().stream()
                                .map(Comercio::getNomeComercio)
                                .collect(Collectors.joining(", "));
        dto.setNomesComercios(nomes);
        return dto;
    }
}
