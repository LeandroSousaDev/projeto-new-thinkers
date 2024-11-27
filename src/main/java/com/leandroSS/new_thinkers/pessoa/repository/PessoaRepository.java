package com.leandroSS.new_thinkers.pessoa.repository;

import java.util.List;

import com.leandroSS.new_thinkers.pessoa.entity.PessoaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaRepository extends JpaRepository<PessoaEntity, Integer> {

    PessoaEntity findByCodigoPessoa(Integer codigoPessoa);

    List<PessoaEntity> findByLogin(String login);

    List<PessoaEntity> findByStatus(Integer status);
}
