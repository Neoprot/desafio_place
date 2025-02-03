package com.placeti.avaliacao.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.Set;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.placeti.avaliacao.dto.CriaComercioDTO;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;

public class ComercioTest {
    

    private Validator validator;
    @BeforeEach
    void setup(){
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Test
    public void testEmptyConstructor() {
        Comercio comercio = new Comercio();
        assertNull(comercio.getId());
        assertNull(comercio.getNomeComercio());
        assertNull(comercio.getNomeResponsavel());
        assertNull(comercio.getTipoComercio());
        assertNull(comercio.getCidade());
    }

    @Test
    public void testAllArgsConstructor() {
        Comercio comercio = new Comercio(1L, "nomeComercio", "nomeResponsavel", TipoComercio.PADARIA, new Cidade());
        assertEquals(1L, comercio.getId());
        assertEquals("nomeComercio", comercio.getNomeComercio());
        assertEquals("nomeResponsavel", comercio.getNomeResponsavel());
        assertEquals(TipoComercio.PADARIA, comercio.getTipoComercio());
        assertEquals(new Cidade(), comercio.getCidade());
    }

    @Test
    public void testCustomConstructor() {
        Comercio comercio = new Comercio(new CriaComercioDTO(1L, "NomeComercio", "NomeResponsavel", TipoComercio.FARMACIA, 1L) , new Cidade());
        assertEquals(1L, comercio.getId());
        assertEquals("NomeComercio", comercio.getNomeComercio());
        assertEquals("NomeResponsavel", comercio.getNomeResponsavel());
        assertEquals(TipoComercio.FARMACIA, comercio.getTipoComercio());
        assertEquals(new Cidade(), comercio.getCidade());
    }

    @Test
    public void shouldNomeBeNotNullable() {
        Comercio comercio = new Comercio(1L, null, "nomeResponsavel", TipoComercio.PADARIA, new Cidade());
        Set<ConstraintViolation<Comercio>> violations = validator.validate(comercio);
        assertEquals(1,violations.size());
        Assertions.assertThat(violations).extracting(ConstraintViolation::getMessage).contains("nome do comercio não deve ser nulo");
    }

    @Test
    public void shouldNomeBeNotSizeOverHundred() {
        Comercio comercio = new Comercio(1L, "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas at odio et est luctus posuere sodal", "nomeResponsavel", TipoComercio.PADARIA, new Cidade());
        Set<ConstraintViolation<Comercio>> violations = validator.validate(comercio);
        assertEquals(1,violations.size());
        Assertions.assertThat(violations).extracting(ConstraintViolation::getMessage).contains("tamanho do nome do comercio deve ser entre 1 e 100");
    }

    @Test
    public void shouldNomeResponsavelBeNotNullable() {
        Comercio comercio = new Comercio(1L, "nomeComercio", null, TipoComercio.PADARIA, new Cidade());
        Set<ConstraintViolation<Comercio>> violations = validator.validate(comercio);
        assertEquals(1,violations.size());
        Assertions.assertThat(violations).extracting(ConstraintViolation::getMessage).contains("nome do responsavel não deve ser nulo");
    }

    @Test
    public void shouldNomeResponsavelBeNotSizeOverHundred() {
        Comercio comercio = new Comercio(1L, "nomeComercio", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas at odio et est luctus posuere sodal", TipoComercio.PADARIA, new Cidade());
        Set<ConstraintViolation<Comercio>> violations = validator.validate(comercio);
        assertEquals(1,violations.size());
        Assertions.assertThat(violations).extracting(ConstraintViolation::getMessage).contains("tamanho do nome do nome do resposavel deve ser entre 1 e 100");
    }

    @Test
    public void shouldTipoComercioBeNotNullable() {
        Comercio comercio = new Comercio(1L, "nomeComercio", "nomeResponsavel", null, new Cidade());
        Set<ConstraintViolation<Comercio>> violations = validator.validate(comercio);
        assertEquals(1,violations.size());
        Assertions.assertThat(violations).extracting(ConstraintViolation::getMessage).contains("Tipo do comercio não deve ser nulo");
    }
}
