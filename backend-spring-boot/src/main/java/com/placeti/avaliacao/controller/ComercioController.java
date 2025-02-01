package com.placeti.avaliacao.controller;

import com.placeti.avaliacao.dto.ComercioDTO;
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
    public ResponseEntity<Comercio> buscarComercio(@PathVariable Long id) {
        return comercioService.buscarComercio(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Comercio>> listarComercios() {
        List<Comercio> lista = comercioService.listarComercios();
        return ResponseEntity.ok(lista);
    }

    @PostMapping
    public ResponseEntity<Comercio> criarComercio(@RequestBody ComercioDTO dto) {
        Comercio salvo = comercioService.salvarComercio(dto);
        return ResponseEntity.ok(salvo);
    }

    @PutMapping
    public ResponseEntity<Comercio> atualizarComercio(@RequestBody ComercioDTO dto) {
        if (dto.getId() == null) {
            return ResponseEntity.badRequest().build();
        }
        Comercio atualizado = comercioService.atualizarComercio(dto);
        return ResponseEntity.ok(atualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarComercio(@PathVariable Long id) {
        comercioService.deletarComercio(id);
        return ResponseEntity.ok().build();
    }
}
