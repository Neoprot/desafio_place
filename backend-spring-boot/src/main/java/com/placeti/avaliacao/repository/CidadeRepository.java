package com.placeti.avaliacao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.placeti.avaliacao.model.Cidade;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Long> {
}
