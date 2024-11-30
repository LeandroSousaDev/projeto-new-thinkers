package com.leandroSS.new_thinkers.UF;

import com.leandroSS.new_thinkers.UF.dto.CreateUfDto;
import com.leandroSS.new_thinkers.UF.dto.ResponseUfDto;
import com.leandroSS.new_thinkers.utils.excepition.NotFoundException;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UfService {
    private final UfRepository ufRepository;

    public UfService(UfRepository ufRepository) {
        this.ufRepository = ufRepository;
    }

    public List<ResponseUfDto> createUf(CreateUfDto createUfDto) {

        var newUf = new UfEntity();
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
                        UFs.getStatus()))
                .toList();
    }

    public List<ResponseUfDto> listAllUF() {
        var allUf = this.ufRepository.findAll();

        return allUf
                .stream()
                .map(uf -> new ResponseUfDto(
                        uf.getCodigoUf(),
                        uf.getSigla(),
                        uf.getNome(),
                        uf.getStatus()))
                .toList();
    }

    public ResponseUfDto ufBySigla(String sigla) {

        var listUf = this.ufRepository.findBySigla(sigla);

        return new ResponseUfDto(
                listUf.getCodigoUf(),
                listUf.getSigla(),
                listUf.getNome(),
                listUf.getStatus());
    }

    public ResponseUfDto ufByNome(String nome) {
        var listUf = this.ufRepository.findByNome(nome);

        return new ResponseUfDto(
                listUf.getCodigoUf(),
                listUf.getSigla(),
                listUf.getNome(),
                listUf.getStatus());
    }

    public ResponseUfDto ufById(Integer codigoUf) {

        var listUf = this.ufRepository.findByCodigoUf(codigoUf);

        return new ResponseUfDto(
                listUf.getCodigoUf(),
                listUf.getSigla(),
                listUf.getNome(),
                listUf.getStatus());
    }

    public List<ResponseUfDto> ufByStatus(Integer status) {
        var listUf = this.ufRepository.findByStatus(status);

        return listUf
                .stream()
                .map(uf -> new ResponseUfDto(
                        uf.getCodigoUf(),
                        uf.getSigla(),
                        uf.getNome(),
                        uf.getStatus()))
                .toList();

    }

    public List<ResponseUfDto> updateUF(String codigoUf, UfEntity uf) throws NotFoundException {

        var id = Integer.valueOf(codigoUf);
        var ufCurrent = this.ufRepository.findByCodigoUf(id);

        if (ufCurrent == null) {
            throw new NotFoundException("Estado não encontrado");
        }

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
                        UFs.getStatus()))
                .toList();

    }
}
