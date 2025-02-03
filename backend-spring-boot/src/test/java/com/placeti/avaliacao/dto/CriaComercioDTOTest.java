package com.placeti.avaliacao.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

import com.placeti.avaliacao.model.TipoComercio;

public class CriaComercioDTOTest {
    
    @Test
    public void ShouldCreateWithNoArgsConstructor(){
        CriaComercioDTO dto = new CriaComercioDTO();
        assertNull(dto.getId());
        assertNull(dto.getNomeComercio());
        assertNull(dto.getNomeResponsavel());
        assertNull(dto.getTipoComercio());
        assertNull(dto.getCidadeId());
    }

    @Test
    public void ShouldCreateWithAllArgsConstructor(){
        CriaComercioDTO dto = new CriaComercioDTO(1L, "nomeComercio", "nomeResponsavel", TipoComercio.LANCHONETE, 1L);
        assertEquals(1L, dto.getId());
        assertEquals("nomeComercio",dto.getNomeComercio());
        assertEquals("nomeResponsavel",dto.getNomeResponsavel());
        assertEquals(TipoComercio.LANCHONETE,dto.getTipoComercio());
        assertEquals(1L,dto.getCidadeId());
    }
}
