package com.placeti.avaliacao.dto;

import com.placeti.avaliacao.model.TipoComercio;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CriaComercioDTO {
    private Long id;
    private String nomeComercio;
    private String nomeResponsavel;
    private TipoComercio tipoComercio;
    private Long cidadeId;
}
