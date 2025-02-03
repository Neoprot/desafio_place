package com.placeti.avaliacao.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.placeti.avaliacao.dto.BuscaCidadeDTO;
import com.placeti.avaliacao.dto.CriaCidadeDTO;
import com.placeti.avaliacao.model.Cidade;
import com.placeti.avaliacao.repository.CidadeRepository;

public class CidadeServiceTest {
    
    @InjectMocks
    private CidadeService cidadeService;

    @Mock
    private CidadeRepository cidadeRepository;

    private AutoCloseable closeable;
    @BeforeEach
    public void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
    }
    
    @AfterEach
    public void tearDown() throws Exception {
        closeable.close();
    }

    @Test
    public void ShouldReturnCidade(){
        Long id = 1L;
        Cidade cidade = new Cidade(1L, "nome", "uf", true, List.of());
        when(cidadeRepository.findById(id)).thenReturn(Optional.of(cidade));
        BuscaCidadeDTO buscaCidadeDTO = cidadeService.pesquisarCidade(id);
        assertEquals(buscaCidadeDTO.getId(), cidade.getId());
        assertEquals(buscaCidadeDTO.getNome(), cidade.getNome());
        assertEquals(buscaCidadeDTO.getUf(), cidade.getUf());
        assertEquals(buscaCidadeDTO.getCapital(), cidade.getCapital());
        assertEquals("", buscaCidadeDTO.getNomesComercios());
    }

    @Test
    public void ShouldReturnNull(){
        Long id = 1L;
        when(cidadeRepository.findById(id)).thenReturn(Optional.empty());
        BuscaCidadeDTO buscaCidadeDTO = cidadeService.pesquisarCidade(id);
        assertEquals(null, buscaCidadeDTO);
    }

    @Test
    public void ShouldReturnCidades(){
        List<Cidade> cidades = List.of(new Cidade(1L, "nome", "uf", true, List.of()));
        when(cidadeRepository.findAll()).thenReturn(cidades);
        List<BuscaCidadeDTO> buscaCidadeDTOs = cidadeService.pesquisarCidades();
        assertEquals(buscaCidadeDTOs.size(), cidades.size());
        assertEquals(buscaCidadeDTOs.get(0).getId(), cidades.get(0).getId());
        assertEquals(buscaCidadeDTOs.get(0).getNome(), cidades.get(0).getNome());
        assertEquals(buscaCidadeDTOs.get(0).getUf(), cidades.get(0).getUf());
        assertEquals(buscaCidadeDTOs.get(0).getCapital(), cidades.get(0).getCapital());
        assertEquals("", buscaCidadeDTOs.get(0).getNomesComercios());
    }

    @Test
    public void ShouldIncludeCidade(){
        CriaCidadeDTO criaCidadeDTO = new CriaCidadeDTO(1L, "nome", "uf", true, "");
        when(cidadeRepository.save(any())).thenReturn(new Cidade());
        org.junit.jupiter.api.Assertions.assertDoesNotThrow(() -> cidadeService.incluirCidade(criaCidadeDTO));
    }

    @Test
    public void ShouldNotIncludeCidade(){
        CriaCidadeDTO criaCidadeDTO = new CriaCidadeDTO(1L, "nome", "uf", true, "");
        when(cidadeRepository.save(any())).thenThrow(new RuntimeException());
        org.junit.jupiter.api.Assertions.assertThrows(RuntimeException.class, () -> cidadeService.incluirCidade(criaCidadeDTO));
    }

    @Test
    public void ShouldChangeCidade(){
        CriaCidadeDTO criaCidadeDTO = new CriaCidadeDTO(1L, "nome", "uf", true, "");
        when(cidadeRepository.existsById(criaCidadeDTO.getId())).thenReturn(true);
        when(cidadeRepository.save(any())).thenReturn(new Cidade());
        org.junit.jupiter.api.Assertions.assertDoesNotThrow(() -> cidadeService.alterarCidade(criaCidadeDTO));
    }

    @Test
    public void ShouldNotChangeCidade(){
        CriaCidadeDTO criaCidadeDTO = new CriaCidadeDTO(1L, "nome", "uf", true, "");
        when(cidadeRepository.existsById(criaCidadeDTO.getId())).thenReturn(false);
        org.junit.jupiter.api.Assertions.assertThrows(IllegalStateException.class, () -> cidadeService.alterarCidade(criaCidadeDTO));
    }

    @Test
    public void ShouldDeleteCidade(){
        Long id = 1L;
        when(cidadeRepository.existsById(id)).thenReturn(true);
        doNothing().when(cidadeRepository).deleteById(id);
        org.junit.jupiter.api.Assertions.assertDoesNotThrow(() -> cidadeService.excluirCidade(id));
    }

    @Test
    public void ShouldNotDeleteWhenIdIsNull(){
        Long id = null;
        org.junit.jupiter.api.Assertions.assertThrows(IllegalArgumentException.class, () -> cidadeService.excluirCidade(id));
    }

    @Test
    public void ShouldNotDeleteWhenCidadeDoesNotExist(){
        Long id = 1L;
        when(cidadeRepository.existsById(id)).thenReturn(false);
        org.junit.jupiter.api.Assertions.assertThrows(IllegalStateException.class, () -> cidadeService.excluirCidade(id));
    }
}
