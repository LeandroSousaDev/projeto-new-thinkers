package com.example.new_thinkers.service;

import com.example.new_thinkers.entity.UF;
import com.example.new_thinkers.repository.UfRepository;
import org.springframework.stereotype.Service;


@Service
public class UfService {

    private UfRepository ufRepository;

    public UfService(UfRepository ufRepository) {
        this.ufRepository = ufRepository;
    }

    public UF createUf(UF uf){

        var UFCriado = this.ufRepository.save(uf);
        return UFCriado;
    }
}
