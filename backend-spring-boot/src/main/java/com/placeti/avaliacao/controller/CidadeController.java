package com.placeti.avaliacao.controller;

import com.placeti.avaliacao.dto.BuscaCidadeDTO;
import com.placeti.avaliacao.dto.CriaCidadeDTO;
import com.placeti.avaliacao.service.CidadeService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cidades")
public class CidadeController {

	
	@Autowired
	private CidadeService projetoService;
	@GetMapping("/{id}")
    public ResponseEntity<BuscaCidadeDTO> buscarPeloId(@PathVariable("id") Long id) {
        BuscaCidadeDTO dto = projetoService.pesquisarCidade(id);
        if(dto != null) {
            return ResponseEntity.ok(dto);
        }
        return ResponseEntity.notFound().build();
    }
	
	@GetMapping
	public List<BuscaCidadeDTO> pesquisarCidades() {
        return projetoService.pesquisarCidades();
    }
	
	@PostMapping
	public void incluirCidade(@RequestBody CriaCidadeDTO cidadeDto) {
			projetoService.incluirCidade(cidadeDto);
	}	
	
	@PutMapping
	public void alterarCidade(@RequestBody CriaCidadeDTO cidadeDto) {
		projetoService.alterarCidade(cidadeDto);
	}
	@DeleteMapping("/{idCidade}")
	public void excluirCidade(@PathVariable("idCidade") Long idCidade) {
		projetoService.excluirCidade(idCidade);
	}	
}
