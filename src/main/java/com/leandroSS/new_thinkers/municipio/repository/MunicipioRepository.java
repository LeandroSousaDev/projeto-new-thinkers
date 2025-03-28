package com.leandroSS.new_thinkers.municipio.repository;

import com.leandroSS.new_thinkers.UF.Entity.UfEntity;

import java.util.List;

import com.leandroSS.new_thinkers.municipio.entity.MunicipioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MunicipioRepository extends JpaRepository<MunicipioEntity, Integer> {

    List<MunicipioEntity> findByUF(UfEntity UF);

    List<MunicipioEntity> findByNome(String nome);

    List<MunicipioEntity> findByStatus(Integer status);

    List<MunicipioEntity> findByCodigoMunicipio(Integer codigoMuniciopio);

}
