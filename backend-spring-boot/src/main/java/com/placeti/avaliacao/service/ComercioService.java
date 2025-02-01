package com.placeti.avaliacao.service;

import com.placeti.avaliacao.dto.ComercioDTO;
import com.placeti.avaliacao.model.Comercio;
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
    private ComercioRepository comercioRepository;

    public Comercio salvarComercio(ComercioDTO dto) {
        Comercio comercio = fromDTO(dto);
        return comercioRepository.save(comercio);
    }

    public Optional<Comercio> buscarComercio(Long id) {
        return comercioRepository.findById(id);
    }

    public List<Comercio> listarComercios() {
        return comercioRepository.findAll();
    }

    public Comercio atualizarComercio(ComercioDTO dto) {
        Comercio comercio = fromDTO(dto);
        return comercioRepository.save(comercio);
    }

    public void deletarComercio(Long id) {
        comercioRepository.deleteById(id);
    }

        private Comercio fromDTO(ComercioDTO dto) {
        Comercio comercio = new Comercio();
        comercio.setId(dto.getId());
        comercio.setNomeComercio(dto.getNomeComercio());
        comercio.setNomeResponsavel(dto.getNomeResponsavel());
        comercio.setTipoComercio(dto.getTipoComercio());
        comercio.setCidade(dto.getCidade());
        return comercio;
    }
}
