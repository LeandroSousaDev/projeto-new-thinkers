package com.leandroSS.new_thinkers.municipio;

import com.leandroSS.new_thinkers.UF.UfEntity;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MunicipioRepository extends JpaRepository<MunicipioEntity, Integer> {

    List<MunicipioEntity> findByUF(UfEntity UF);

    List<MunicipioEntity> findByNome(String nome);

    List<MunicipioEntity> findByStatus(Integer status);

    List<MunicipioEntity> findByCodigoMunicipio(Integer codigoMuniciopio);

}
