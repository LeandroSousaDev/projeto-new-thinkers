package com.leandroSS.new_thinkers.repository;

import com.leandroSS.new_thinkers.entity.Bairro;
import com.leandroSS.new_thinkers.entity.Municipio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BairroRepository extends JpaRepository<Bairro, Integer> {
    List<Bairro> findByMunicipio(Municipio municipio);

    List<Bairro> findByNome(String nome);

    List<Bairro> findByStatus(Integer status);

    Bairro findByCodigoBairro(Integer codigoBairro);

}
