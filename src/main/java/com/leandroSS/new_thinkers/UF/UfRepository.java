package com.leandroSS.new_thinkers.UF;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UfRepository extends JpaRepository<UfEntity, Integer> {

    List<UfEntity> findByCodigoUF(Integer codigoUF);

    List<UfEntity> findBySigla(String sigla);

    List<UfEntity> findByNome(String nome);

    List<UfEntity> findByStatus(Integer status);

}
