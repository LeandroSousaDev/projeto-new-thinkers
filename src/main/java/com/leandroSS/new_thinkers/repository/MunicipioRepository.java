package com.leandroSS.new_thinkers.repository;

import com.leandroSS.new_thinkers.entity.Municipio;
import com.leandroSS.new_thinkers.entity.UF;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MunicipioRepository extends JpaRepository<Municipio, Integer> {

    List<Municipio> findByUf(UF uf);

    List<Municipio> findByNome(String nome);

    List<Municipio> findByStatus(Integer status);

    Municipio findByCodigoMunicipio(Integer codigoMuniciopio);

}
