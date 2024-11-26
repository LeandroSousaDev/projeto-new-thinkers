package com.leandroSS.new_thinkers.pessoa.repository;

import java.util.List;

import com.leandroSS.new_thinkers.pessoa.entity.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaRepository extends JpaRepository<Pessoa, Integer> {

    Pessoa findByCodigoPessoa(Integer codigoPessoa);

    List<Pessoa> findByLogin(String login);

    List<Pessoa> findByStatus(Integer status);
}
