package com.placeti.avaliacao.service;

import com.placeti.avaliacao.dto.CriarComercioDTO;
import com.placeti.avaliacao.model.Cidade;
import com.placeti.avaliacao.model.Comercio;
import com.placeti.avaliacao.repository.CidadeRepository;
import com.placeti.avaliacao.repository.ComercioRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ComercioService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ProjetoService projetoService;

    @Autowired
    private ComercioRepository comercioRepository;

    public Comercio salvarComercio(CriarComercioDTO dto) {
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

    public Comercio atualizarComercio(CriarComercioDTO dto) {
        Cidade cidade = projetoService.buscarCidade(dto.getCidadeId()).orElseThrow(() -> new RuntimeException("Cidade não encontrada"));
        
        Comercio comercio = new Comercio(dto, cidade);
        return comercioRepository.save(comercio);
    }

    public void deletarComercio(Long id) {
        comercioRepository.deleteById(id);
    }


}
