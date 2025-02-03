package com.placeti.avaliacao.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

import com.placeti.avaliacao.model.Cidade;
import com.placeti.avaliacao.model.Comercio;
import com.placeti.avaliacao.model.TipoComercio;

public class BuscaComercioDTOTest {
    

    @Test
    public void ShouldCreateWithEmptyConstructor(){
        BuscaComercioDTO dto = new BuscaComercioDTO();
        assertNull(dto.getId());
        assertNull(dto.getNomeComercio());
        assertNull(dto.getNomeResponsavel());
        assertNull(dto.getTipoComercio());
        assertNull(dto.getCidadeId());
    }

    @Test
    public void ShouldBeNullIfComercioNullToDTO(){
        BuscaComercioDTO dto = BuscaComercioDTO.toDTO(null);
        assertNull(dto);
    }

    @Test
    public void ShouldConvertToDTO(){
        BuscaComercioDTO dto = BuscaComercioDTO.toDTO(null);
        Comercio comercio = new Comercio(1L, "comercio","Paulo",TipoComercio.PADARIA, new Cidade(1L, "nome", "uf", true, null));
        dto = BuscaComercioDTO.toDTO(comercio);
        assertEquals(1L, dto.getId());
        assertEquals("comercio", dto.getNomeComercio());
        assertEquals("Paulo", dto.getNomeResponsavel());
        assertEquals(TipoComercio.PADARIA, dto.getTipoComercio());
        assertEquals(1L, dto.getCidadeId());
    }
}
