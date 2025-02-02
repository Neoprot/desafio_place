package com.placeti.avaliacao.service;

import com.placeti.avaliacao.dto.CidadeDTO;
import com.placeti.avaliacao.model.Cidade;
import com.placeti.avaliacao.repository.CidadeRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

//------------------------------------------------------------------
/** Service usado para acessar os repositórios da aplicação */
//------------------------------------------------------------------
@Service
public class ProjetoService {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
    private CidadeRepository cidadeRepository;

	//---------------------------------------------------------
	/** Método que busca uma cidade pelo seu ID */
	//---------------------------------------------------------
	    public CidadeDTO pesquisarCidade(Long id) {
        Optional<Cidade> optCidade = buscarCidade(id);
        if(optCidade.isPresent()){
            return CidadeDTO.toDTO(optCidade.get());
        }
        logger.warn("Cidade com id {} não encontrada", id);
        return null;
    }

    public Optional<Cidade> buscarCidade(Long id) {
        return cidadeRepository.findById(id);
    }

	//---------------------------------------------------------
	/** Método que retorna todas as cidades cadastradas */
	//---------------------------------------------------------
    public List<CidadeDTO> pesquisarCidades() {
        List<Cidade> cidades = cidadeRepository.findAll();
        return cidades.stream().map(CidadeDTO::toDTO).collect(Collectors.toList());
    }

	//----------------------------------------------------------
	/** Método chamado para incluir uma nova cidade */
	//----------------------------------------------------------	
    public void incluirCidade(CidadeDTO dto) {
        Cidade cidade = fromDTO(dto);
        cidadeRepository.save(cidade);
        logger.info("Cidade {} incluída com sucesso", cidade.getNome());
    }

	//----------------------------------------------------------
	/** Método chamado para alterar os dados de uma cidade */
	//----------------------------------------------------------
    public void alterarCidade(CidadeDTO dto) {
        // Verifica se a cidade existe
        if(dto.getId() == null || !cidadeRepository.existsById(dto.getId())) {
            logger.warn("Tentativa de alterar cidade inexistente com id {}", dto.getId());
            return;
        }
        Cidade cidade = fromDTO(dto);
        cidadeRepository.save(cidade);
        logger.info("Cidade {} alterada com sucesso", cidade.getNome());
    }

	//----------------------------------------------------------
	/** Método chamado para excluir uma cidade */
	//----------------------------------------------------------	
    public void excluirCidade(Long idCidade) {
		if (idCidade == null) {
			logger.warn("Id de cidade nulo para exclusão.");
			throw new IllegalArgumentException("O id da cidade não pode ser nulo.");
		}
		if (cidadeRepository.existsById(idCidade)) {
			cidadeRepository.deleteById(idCidade);
			logger.info("Cidade com id {} excluída", idCidade);
		} else {
			logger.warn("Tentativa de excluir cidade inexistente com id {}", idCidade);
		}
	}
	
    private Cidade fromDTO(CidadeDTO dto) {
        Cidade cidade = new Cidade();
        cidade.setId(dto.getId());
        cidade.setNome(dto.getNome());
        cidade.setUf(dto.getUf());
        cidade.setCapital(dto.getCapital());
        return cidade;
    }
}
