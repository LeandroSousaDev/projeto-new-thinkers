package com.leandroSS.new_thinkers.repository;

import com.leandroSS.new_thinkers.entity.Pessoa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaRepository extends JpaRepository<Pessoa, Integer> {

    Pessoa findByCodigoPessoa(Integer codigoPessoa);

    List<Pessoa> findByLogin(String login);

    List<Pessoa> findByStatus(Integer status);
}
