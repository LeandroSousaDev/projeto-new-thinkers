package com.leandroSS.new_thinkers.service;

import com.leandroSS.new_thinkers.dto.UF.CreateUfDto;
import com.leandroSS.new_thinkers.dto.UF.ResponseUfDto;
import com.leandroSS.new_thinkers.entity.UF;
import com.leandroSS.new_thinkers.repository.UfRepository;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class UfService {
    private final UfRepository ufRepository;
    public UfService(UfRepository ufRepository) {
        this.ufRepository = ufRepository;
    }

    public List<ResponseUfDto> createUf(CreateUfDto createUfDto){

        var newUf = new UF();
        newUf.setSigla(createUfDto.sigla());
        newUf.setNome(createUfDto.nome());
        newUf.setStatus(createUfDto.status());

        this.ufRepository.save(newUf);

        var allUf = this.ufRepository.findAll();

        return allUf
                .stream()
                .map(UFs -> new ResponseUfDto(
                        UFs.getCodigoUf(),
                        UFs.getSigla(),
                        UFs.getNome(),
                        UFs.getStatus()
                )).toList();

    }

    public List<ResponseUfDto> listAllUF() {
        var allUf = this.ufRepository.findAll();

        return allUf
                .stream()
                .map(uf -> new ResponseUfDto(
                        uf.getCodigoUf(),
                        uf.getSigla(),
                        uf.getNome(),
                        uf.getStatus()
                )).toList();
    }

    public List<ResponseUfDto> updateUF(String codigoUf, UF uf) {

        var id = Integer.valueOf(codigoUf);
        var ufCurrent = this.ufRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatusCode.valueOf(404), "Estado não exixste"));

        if (uf.getNome() != null) {
            ufCurrent.setNome(uf.getNome());
        }

        if (uf.getSigla() != null) {
            ufCurrent.setSigla(uf.getSigla());
        }

        if (uf.getStatus() != null) {
            ufCurrent.setStatus(uf.getStatus());
        }

        this.ufRepository.save(ufCurrent);

        var allUf = this.ufRepository.findAll();

        return allUf
                .stream()
                .map(UFs -> new ResponseUfDto(
                        UFs.getCodigoUf(),
                        UFs.getSigla(),
                        UFs.getNome(),
                        UFs.getStatus()
                )).toList();

    }
}
