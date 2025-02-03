package com.placeti.avaliacao.service;

import com.placeti.avaliacao.dto.BuscaComercioDTO;
import com.placeti.avaliacao.dto.CriaComercioDTO;
import com.placeti.avaliacao.model.Cidade;
import com.placeti.avaliacao.model.Comercio;
import com.placeti.avaliacao.repository.ComercioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ComercioService {

    @Autowired
    private CidadeService projetoService;

    @Autowired
    private ComercioRepository comercioRepository;

    public CriaComercioDTO salvarComercio(CriaComercioDTO dto) {
        Cidade cidade = projetoService.buscarCidade(dto.getCidadeId()).orElseThrow(() -> new RuntimeException("Cidade não encontrada"));
        
        Comercio comercioSalvo = comercioRepository.save(new Comercio(dto, cidade));
        dto.setId(comercioSalvo.getId());
        return dto;
    }

    public Optional<BuscaComercioDTO> buscarComercio(Long id) {
        Comercio comercio = comercioRepository.findById(id).orElseThrow(() -> new RuntimeException("Comercio não encontrado"));
        return Optional.of(BuscaComercioDTO.toDTO(comercio));
    }

    public List<BuscaComercioDTO> listarComercios() {
        List<Comercio> comercios = comercioRepository.findAll();
        return comercios.stream().map(BuscaComercioDTO::toDTO).collect(Collectors.toList());
    }

    public CriaComercioDTO atualizarComercio(CriaComercioDTO dto) {
        Cidade cidade = projetoService.buscarCidade(dto.getCidadeId()).orElseThrow(() -> new RuntimeException("Cidade não encontrada"));
        
        comercioRepository.save(new Comercio(dto, cidade));
        return dto;

    }

    public void deletarComercio(Long idComercio) {
        if (idComercio == null) {
            throw new RuntimeException("Id do comercio não pode ser nulo.");
        }
        if(comercioRepository.existsById(idComercio)){
            comercioRepository.deleteById(idComercio);
        }else{
        throw new RuntimeException("Comercio não encontrado");
        }
    }
}
