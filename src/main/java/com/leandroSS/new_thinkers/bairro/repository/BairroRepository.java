package com.leandroSS.new_thinkers.bairro.repository;

import com.leandroSS.new_thinkers.bairro.entity.BairroEntity;
import com.leandroSS.new_thinkers.municipio.entity.MunicipioEntity;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BairroRepository extends JpaRepository<BairroEntity, Integer> {
    List<BairroEntity> findByMunicipio(MunicipioEntity municipio);

    List<BairroEntity> findByNome(String nome);

    List<BairroEntity> findByStatus(Integer status);

    List<BairroEntity> findByCodigoBairro(Integer codigoBairro);

}
