package com.placeti.avaliacao.service;

import com.placeti.avaliacao.dto.CriaComercioDTO;
import com.placeti.avaliacao.model.Cidade;
import com.placeti.avaliacao.model.Comercio;
import com.placeti.avaliacao.repository.ComercioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ComercioService {

    @Autowired
    private CidadeService projetoService;

    @Autowired
    private ComercioRepository comercioRepository;

    public Comercio salvarComercio(CriaComercioDTO dto) {
        Cidade cidade = projetoService.buscarCidade(dto.getCidadeId()).orElseThrow(() -> new RuntimeException("Cidade não encontrada"));
        
        Comercio comercio = new Comercio(dto, cidade);
        return comercioRepository.save(comercio);
    }

    public Optional<Comercio> buscarComercio(Long id) {
        return comercioRepository.findById(id);
    }

    public List<Comercio> listarComercios() {
        return comercioRepository.findAll();
    }

    public Comercio atualizarComercio(CriaComercioDTO dto) {
        Cidade cidade = projetoService.buscarCidade(dto.getCidadeId()).orElseThrow(() -> new RuntimeException("Cidade não encontrada"));
        
        Comercio comercio = new Comercio(dto, cidade);
        return comercioRepository.save(comercio);
    }

    public void deletarComercio(Long id) {
        comercioRepository.deleteById(id);
    }


}
