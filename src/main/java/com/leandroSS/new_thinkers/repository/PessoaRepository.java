package com.leandroSS.new_thinkers.repository;

import com.leandroSS.new_thinkers.entity.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaRepository extends JpaRepository<Pessoa, Integer> {
}
