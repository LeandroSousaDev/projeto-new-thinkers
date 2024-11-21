package com.leandroSS.new_thinkers.repository;

import com.leandroSS.new_thinkers.entity.UF;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UfRepository extends JpaRepository<UF, Integer> {
    UF findBySigla(String sigla);

    UF findByNome(String nome);

    List<UF> findByStatus(Integer status);

    UF findByCodigoUf(Integer codigoUf);
}
