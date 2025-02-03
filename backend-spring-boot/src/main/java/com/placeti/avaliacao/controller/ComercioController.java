package com.placeti.avaliacao.controller;

import com.placeti.avaliacao.dto.BuscaComercioDTO;
import com.placeti.avaliacao.dto.CriaComercioDTO;
import com.placeti.avaliacao.model.Comercio;
import com.placeti.avaliacao.service.ComercioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comercios")
public class ComercioController {

    @Autowired
    private ComercioService comercioService;

    @GetMapping("/{id}")
    public ResponseEntity<BuscaComercioDTO> buscarComercio(@PathVariable Long id) {
        return comercioService.buscarComercio(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<BuscaComercioDTO>> listarComercios() {
        List<BuscaComercioDTO> lista = comercioService.listarComercios();
        return ResponseEntity.ok(lista);
    }

    @PostMapping
    public ResponseEntity<CriaComercioDTO> criarComercio(@RequestBody CriaComercioDTO dto) {
        CriaComercioDTO salvo = comercioService.salvarComercio(dto);
        return ResponseEntity.ok(salvo);
    }

    @PutMapping
    public ResponseEntity<CriaComercioDTO> atualizarComercio(@RequestBody CriaComercioDTO dto) {
        if (dto.getId() == null) {
            return ResponseEntity.badRequest().build();
        }
        CriaComercioDTO atualizado = comercioService.atualizarComercio(dto);
        return ResponseEntity.ok(atualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarComercio(@PathVariable Long id) {
        comercioService.deletarComercio(id);
        return ResponseEntity.ok().build();
    }
}
