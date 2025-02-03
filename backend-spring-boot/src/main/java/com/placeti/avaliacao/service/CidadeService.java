package com.placeti.avaliacao.service;

import com.placeti.avaliacao.dto.BuscaCidadeDTO;
import com.placeti.avaliacao.dto.CriaCidadeDTO;
import com.placeti.avaliacao.model.Cidade;
import com.placeti.avaliacao.repository.CidadeRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CidadeService {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
    private CidadeRepository cidadeRepository;

	public BuscaCidadeDTO pesquisarCidade(Long id) {
        Optional<Cidade> optCidade = buscarCidade(id);
        if(optCidade.isPresent()){
            return BuscaCidadeDTO.toDTO(optCidade.get());
        }
        logger.warn("Cidade com id {} não encontrada", id);
        return null;
    }

    public Optional<Cidade> buscarCidade(Long id) {
        return cidadeRepository.findById(id);
    }

    public List<BuscaCidadeDTO> pesquisarCidades() {
        List<Cidade> cidades = cidadeRepository.findAll();
        return cidades.stream().map(BuscaCidadeDTO::toDTO).collect(Collectors.toList());
    }

    public void incluirCidade(CriaCidadeDTO dto) {
        Cidade cidade = new Cidade(dto);
        cidadeRepository.save(cidade);
        logger.info("Cidade {} incluída com sucesso", cidade.getNome());
    }

    public void alterarCidade(CriaCidadeDTO dto) {
        if(dto.getId() == null || !cidadeRepository.existsById(dto.getId())) {
            throw new IllegalStateException("Tentativa de alterar cidade inexistente com id "+ dto.getId());
        }
        Cidade cidade = new Cidade(dto);
        cidadeRepository.save(cidade);
        logger.info("Cidade {} alterada com sucesso", cidade.getNome());
    }

    public void excluirCidade(Long idCidade) {
		if (idCidade == null) {
			logger.warn("Id de cidade nulo para exclusão.");
			throw new IllegalArgumentException("O id da cidade não pode ser nulo.");
		}
		if (cidadeRepository.existsById(idCidade)) {
			cidadeRepository.deleteById(idCidade);
			logger.info("Cidade com id {} excluída", idCidade);
		} else {
            throw new IllegalStateException("Tentativa de excluir cidade inexistente com id "+ idCidade);
		}
	}
}
