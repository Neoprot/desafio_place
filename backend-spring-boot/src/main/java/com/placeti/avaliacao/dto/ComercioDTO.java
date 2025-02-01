package com.placeti.avaliacao.dto;

import com.placeti.avaliacao.model.Cidade;
import com.placeti.avaliacao.model.Comercio;
import com.placeti.avaliacao.model.TipoComercio;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ComercioDTO {
    private Long id;
    private String nomeComercio;
    private String nomeResponsavel;
    private TipoComercio tipoComercio;
    private Cidade cidade;

    public static ComercioDTO toDTO(Comercio comercio) {
        if (comercio == null) {
            return null;
        }
        ComercioDTO dto = new ComercioDTO();
        dto.setId(comercio.getId());
        dto.setNomeComercio(comercio.getNomeComercio());
        dto.setNomeResponsavel(comercio.getNomeResponsavel());
        dto.setTipoComercio(comercio.getTipoComercio());
        dto.setCidade(comercio.getCidade());
        return dto;
    }
}
