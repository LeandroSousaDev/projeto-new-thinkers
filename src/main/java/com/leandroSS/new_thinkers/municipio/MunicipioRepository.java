package com.leandroSS.new_thinkers.municipio;

import com.leandroSS.new_thinkers.UF.UF;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MunicipioRepository extends JpaRepository<Municipio, Integer> {

    List<Municipio> findByUf(UF uf);

    List<Municipio> findByNome(String nome);

    List<Municipio> findByStatus(Integer status);

    Municipio findByCodigoMunicipio(Integer codigoMuniciopio);

}
