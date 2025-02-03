package com.placeti.avaliacao.dto;

import com.placeti.avaliacao.model.Comercio;
import com.placeti.avaliacao.model.TipoComercio;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BuscaComercioDTO {
    private Long id;
    private String nomeComercio;
    private String nomeResponsavel;
    private TipoComercio tipoComercio;
    private Long cidadeId;

    public static BuscaComercioDTO toDTO(Comercio comercio) {
        if (comercio == null) {
            return null;
        }
        BuscaComercioDTO dto = new BuscaComercioDTO();
        dto.setId(comercio.getId());
        dto.setNomeComercio(comercio.getNomeComercio());
        dto.setNomeResponsavel(comercio.getNomeResponsavel());
        dto.setTipoComercio(comercio.getTipoComercio());
        dto.setCidadeId(comercio.getCidade().getId());
        return dto;
    }
}
