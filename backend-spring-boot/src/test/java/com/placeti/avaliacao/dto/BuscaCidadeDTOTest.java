package com.placeti.avaliacao.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.placeti.avaliacao.model.Cidade;
import com.placeti.avaliacao.model.Comercio;
import com.placeti.avaliacao.model.TipoComercio;

public class BuscaCidadeDTOTest {
    

    @Test
    public void ShouldCreateWithEmptyConstructor(){
        BuscaCidadeDTO dto = new BuscaCidadeDTO();
        assertNull(dto.getId());
        assertNull(dto.getNome());
        assertNull(dto.getUf());
        assertNull(dto.getCapital());
        assertNull(dto.getNomesComercios());
    }

    @Test
    public void ShouldBeNullIfCidadeNullToDTO(){
        BuscaCidadeDTO dto = BuscaCidadeDTO.toDTO(null);
        assertNull(dto);
    }

    @Test
    public void ShouldConvertToDTO(){
        BuscaCidadeDTO dto = BuscaCidadeDTO.toDTO(null);
        Cidade cidade = new Cidade(1L, "nome", "uf", true, null);
        Comercio comercios = new Comercio(1L, "comercio","Paulo",TipoComercio.PADARIA, cidade);
        cidade.setComercios(List.of(comercios));
        dto = BuscaCidadeDTO.toDTO(cidade);
        assertEquals(1L,dto.getId());
        assertEquals("nome",dto.getNome());
        assertEquals("uf", dto.getUf());
        assertEquals(true,dto.getCapital());
        assertEquals("comercio",dto.getNomesComercios());
    }
}
