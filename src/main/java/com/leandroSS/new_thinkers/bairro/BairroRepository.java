package com.leandroSS.new_thinkers.bairro;

import com.leandroSS.new_thinkers.municipio.Municipio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BairroRepository extends JpaRepository<Bairro, Integer> {
    List<Bairro> findByMunicipio(Municipio municipio);

    List<Bairro> findByNome(String nome);

    List<Bairro> findByStatus(Integer status);

    Bairro findByCodigoBairro(Integer codigoBairro);

}
