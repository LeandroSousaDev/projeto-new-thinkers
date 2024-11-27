package com.leandroSS.new_thinkers.UF;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UfRepository extends JpaRepository<UfEntity, Integer> {
    UfEntity findBySigla(String sigla);

    UfEntity findByNome(String nome);

    List<UfEntity> findByStatus(Integer status);

    UfEntity findByCodigoUf(Integer codigoUf);
}
