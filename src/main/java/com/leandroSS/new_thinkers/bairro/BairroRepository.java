package com.leandroSS.new_thinkers.bairro;

import com.leandroSS.new_thinkers.municipio.MunicipioEntity;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BairroRepository extends JpaRepository<BairroEntity, Integer> {
    List<BairroEntity> findByMunicipio(MunicipioEntity municipio);

    List<BairroEntity> findByNome(String nome);

    List<BairroEntity> findByStatus(Integer status);

    List<BairroEntity> findByCodigoBairro(Integer codigoBairro);

}
