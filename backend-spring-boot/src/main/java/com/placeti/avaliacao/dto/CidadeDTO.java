package com.placeti.avaliacao.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.placeti.avaliacao.model.Cidade;
import com.placeti.avaliacao.model.Comercio;

import lombok.Data;
import lombok.NoArgsConstructor;

//-------------------------------------------------
/** DTO que guarda os dados de uma cidade */
//-------------------------------------------------
@Data
@NoArgsConstructor
public class CidadeDTO {

	//---------------------------------------
	// Atributos do DTO
	//---------------------------------------
    private Long id;
    private String nome;
    private String uf;
    private Boolean capital;
    private List<String> nomesComercios;
    
    
    //-----------------------------------------------
    /** Carrega o DTO com dados da entidade */
    //-----------------------------------------------
    public static CidadeDTO toDTO(Cidade cidade) {
        if(cidade == null) {
            return null;
        }
        CidadeDTO dto = new CidadeDTO();
        dto.setId(cidade.getId());
        dto.setNome(cidade.getNome());
        dto.setUf(cidade.getUf());
        dto.setCapital(cidade.getCapital());
                List<String> nomes = cidade.getComercios().stream()
                .map(Comercio::getNomeComercio)
                .collect(Collectors.toList());
        dto.setNomesComercios(nomes);
        return dto;
    }
}
