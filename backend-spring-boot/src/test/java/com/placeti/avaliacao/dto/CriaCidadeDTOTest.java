package com.placeti.avaliacao.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.placeti.avaliacao.model.Cidade;
import com.placeti.avaliacao.model.Comercio;
import com.placeti.avaliacao.model.TipoComercio;

public class CriaCidadeDTOTest {
    
    @Test
    public void ShouldCreateWithEmptyConstructor(){
        CriaCidadeDTO dto = new CriaCidadeDTO();
        assertNull(dto.getId());
        assertNull(dto.getNome());
        assertNull(dto.getUf());
        assertNull(dto.getCapital());
        assertNull(dto.getNomesComercios());
    }

    @Test
    public void ShouldCreateWithAllArgsConstructor(){
        CriaCidadeDTO dto = new CriaCidadeDTO(1L, "nome", "uf", true, "comercio");
        assertEquals(dto.getId(), 1L);
        assertEquals(dto.getNome(), "nome");
        assertEquals(dto.getUf(), "uf");
        assertEquals(dto.getCapital(), true);
        assertEquals(dto.getNomesComercios(), "comercio");
    }

    @Test
    public void ShouldConvertToDTO(){
        CriaCidadeDTO dto = CriaCidadeDTO.toDTO(null);
        assertNull(dto);

        Cidade cidade = new Cidade(1L, "nome", "uf", true, null);
        Comercio comercios = new Comercio(1L, "comercio","Paulo",TipoComercio.PADARIA, cidade);
        cidade.setComercios(List.of(comercios));
        dto = CriaCidadeDTO.toDTO(cidade);
        assertEquals(dto.getId(), 1L);
        assertEquals(dto.getNome(), "nome");
        assertEquals(dto.getUf(), "uf");
        assertEquals(dto.getCapital(), true);
        assertEquals(dto.getNomesComercios(), "comercio");
    }
}
