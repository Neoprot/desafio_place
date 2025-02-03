package com.placeti.avaliacao.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.placeti.avaliacao.dto.CriaCidadeDTO;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;


public class CidadeTest {

    private Validator validator;
    @BeforeEach
    void setup(){
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Test
    public void testEmptyConstructor() {
        Cidade cidade = new Cidade();
        assertNull(cidade.getId());
        assertNull(cidade.getNome());
        assertNull(cidade.getUf());
        assertNull(cidade.getCapital());
        assertEquals(0,cidade.getComercios().size());
    }

    @Test
    public void testConstructor() {
        Cidade cidade = new Cidade(new CriaCidadeDTO(1L, "nome", "uf", true, ""));
        assertEquals(1L, cidade.getId());
        assertEquals("nome", cidade.getNome());
        assertEquals("uf", cidade.getUf());
        assertEquals(true, cidade.getCapital());
        assertEquals(0,cidade.getComercios().size());
    }

    @Test
    public void shouldNomeBeNotNullable() {
        Cidade cidade = new Cidade(new CriaCidadeDTO(1L, null, "uf", true, ""));
        Set<ConstraintViolation<Cidade>> violations = validator.validate(cidade);
        assertEquals(1,violations.size());
        org.assertj.core.api.Assertions.assertThat(violations).extracting(ConstraintViolation::getMessage).contains("nome não deve ser nulo");
    }

    @Test
    public void shouldNomeBeNotSizeOverHundred() {
        Cidade cidade = new Cidade(new CriaCidadeDTO(1L, "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas at odio et est luctus posuere sodal", "uf", true, ""));
        Set<ConstraintViolation<Cidade>> violations = validator.validate(cidade);
        assertEquals(1,violations.size());
        org.assertj.core.api.Assertions.assertThat(violations).extracting(ConstraintViolation::getMessage).contains("tamanho do nome deve ser entre 1 e 100");
    }

    @Test
    public void shouldUfBeNotNullable() {
        Cidade cidade = new Cidade(new CriaCidadeDTO(1L, "nome", null, true, ""));
        Set<ConstraintViolation<Cidade>> violations = validator.validate(cidade);
        assertEquals(1,violations.size());
        org.assertj.core.api.Assertions.assertThat(violations).extracting(ConstraintViolation::getMessage).contains("uf não deve ser nulo");
    }
    
    @Test
    public void shouldUfBeNotSizeOverTwo() {
        Cidade cidade = new Cidade(new CriaCidadeDTO(1L, "nome", "USF", true, ""));
        Set<ConstraintViolation<Cidade>> violations = validator.validate(cidade);
        assertEquals(1,violations.size());
        org.assertj.core.api.Assertions.assertThat(violations).extracting(ConstraintViolation::getMessage).contains("tamanho do uf deve ser entre 1 e 2");
    }

    @Test
    public void shouldCapitalBeNotNullable() {
        Cidade cidade = new Cidade(new CriaCidadeDTO(1L, "nome", "UF", null, ""));
        Set<ConstraintViolation<Cidade>> violations = validator.validate(cidade);
        assertEquals(1,violations.size());
        org.assertj.core.api.Assertions.assertThat(violations).extracting(ConstraintViolation::getMessage).contains("capital não deve ser nulo");
    }
}
