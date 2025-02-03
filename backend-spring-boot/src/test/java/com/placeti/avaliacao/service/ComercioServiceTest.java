package com.placeti.avaliacao.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.placeti.avaliacao.dto.BuscaComercioDTO;
import com.placeti.avaliacao.dto.CriaComercioDTO;
import com.placeti.avaliacao.model.Cidade;
import com.placeti.avaliacao.model.Comercio;
import com.placeti.avaliacao.model.TipoComercio;
import com.placeti.avaliacao.repository.ComercioRepository;

public class ComercioServiceTest {
    
    @InjectMocks
    private ComercioService comercioService;

    @Mock
    private ComercioRepository comercioRepository;
    @Mock
    private CidadeService cidadeService;

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
    public void ShouldCreateComercio(){
        Long id = 1L;
        Cidade cidade = new Cidade(1L, "nome", "uf", true, List.of());
        when(cidadeService.buscarCidade(id)).thenReturn(Optional.of(cidade));
        CriaComercioDTO criaComercioDTO = new CriaComercioDTO(1L, "nomeComercio", "nomeResponsavel", TipoComercio.PADARIA, 1L);
        Comercio comercio = new Comercio(criaComercioDTO, cidade);
        when(comercioRepository.save(any(Comercio.class))).thenReturn(comercio);
        assertEquals(criaComercioDTO, comercioService.salvarComercio(criaComercioDTO));
    }

    @Test
    public void ShouldNotCreateComercioWhenCidadeNotFound(){
        Long id = 1L;
        when(cidadeService.buscarCidade(id)).thenReturn(Optional.empty());
        CriaComercioDTO criaComercioDTO = new CriaComercioDTO(1L, "nomeComercio", "nomeResponsavel", TipoComercio.PADARIA, 1L);
        Assertions.assertThrows(RuntimeException.class, () -> {
            comercioService.salvarComercio(criaComercioDTO);
        });
    }

    @Test
    public void ShouldReturnComercio(){
        Long id = 1L;
        Cidade cidade = new Cidade(1L, "nome", "uf", true, List.of());
        Comercio comercio = new Comercio(1L, "nomeComercio", "nomeResponsavel", TipoComercio.PADARIA, cidade);
        when(comercioRepository.findById(id)).thenReturn(Optional.of(comercio));
        BuscaComercioDTO buscaComercioDTO = comercioService.buscarComercio(id).get();
        assertEquals(buscaComercioDTO.getId(), comercio.getId());
        assertEquals(buscaComercioDTO.getNomeComercio(), comercio.getNomeComercio());
        assertEquals(buscaComercioDTO.getNomeResponsavel(), comercio.getNomeResponsavel());
        assertEquals(buscaComercioDTO.getTipoComercio(), comercio.getTipoComercio());
    }

    @Test
    public void ShouldReturnComercios(){
    List<Comercio> comercios = List.of(new Comercio(1L, "nomeComercio", "nomeResponsavel", TipoComercio.PADARIA, new Cidade(1L, "nome", "uf", true, List.of())));
    when(comercioRepository.findAll()).thenReturn(comercios);
    List<BuscaComercioDTO> BuscaComercioDTOS = comercioService.listarComercios();
    assertEquals(BuscaComercioDTOS.size(), comercios.size());
    assertEquals(BuscaComercioDTOS.get(0).getId(), comercios.get(0).getId());
    assertEquals(BuscaComercioDTOS.get(0).getNomeComercio(), comercios.get(0).getNomeComercio());
    assertEquals(BuscaComercioDTOS.get(0).getNomeResponsavel(), comercios.get(0).getNomeResponsavel());
    assertEquals(BuscaComercioDTOS.get(0).getTipoComercio(), comercios.get(0).getTipoComercio());
    }

    @Test
    public void ShouldChangeComercio(){
        Long id = 1L;
        Cidade cidade = new Cidade(1L, "nome", "uf", true, List.of());
        when(cidadeService.buscarCidade(id)).thenReturn(Optional.of(cidade));
        CriaComercioDTO criaComercioDTO = new CriaComercioDTO(1L, "nomeComercio", "nomeResponsavel", TipoComercio.PADARIA, 1L);
        Comercio comercio = new Comercio(1L, "nomeComercio", "nomeResponsavel", TipoComercio.PADARIA, cidade);
        when(comercioRepository.save(any(Comercio.class))).thenReturn(comercio);
        assertEquals(criaComercioDTO, comercioService.atualizarComercio(criaComercioDTO));
    }

    @Test
    public void ShouldNotChangeWhenCidadeNotFound(){
        Long id = 1L;
        when(cidadeService.buscarCidade(id)).thenReturn(Optional.empty());
        CriaComercioDTO criaComercioDTO = new CriaComercioDTO(1L, "nomeComercio", "nomeResponsavel", TipoComercio.PADARIA, 1L);
        Assertions.assertThrows(RuntimeException.class, () -> {
            comercioService.atualizarComercio(criaComercioDTO);
        });
    }

    @Test
    public void ShouldDeleteComercio(){
        Long id = 1L;
        when(comercioRepository.existsById(id)).thenReturn(true);
        Assertions.assertDoesNotThrow(() -> {
            comercioService.deletarComercio(id);
        });
    }

    @Test
    public void ShouldNotDeleteComercioWhenIdIsNull(){
        Long id = null;
        Assertions.assertThrows(RuntimeException.class, () -> {
            comercioService.deletarComercio(id);
        });
    }

    @Test
    public void ShouldNotDeleteComercioWhenComercioNotFound(){
        Long id = 1L;
        when(comercioRepository.existsById(id)).thenReturn(false);
        Assertions.assertThrows(RuntimeException.class, () -> {
            comercioService.deletarComercio(id);
        });
    }
}
