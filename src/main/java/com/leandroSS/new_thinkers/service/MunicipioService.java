package com.leandroSS.new_thinkers.service;

import com.leandroSS.new_thinkers.dto.ResponseMunicipioDto;
import com.leandroSS.new_thinkers.entity.Municipio;
import com.leandroSS.new_thinkers.repository.MunicipioRepository;
import com.leandroSS.new_thinkers.repository.UfRepository;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class MunicipioService {

    private final MunicipioRepository municipioRepository;
    private final UfRepository ufRepository;

    public MunicipioService(MunicipioRepository municipioRepository, UfRepository ufRepository) {
        this.municipioRepository = municipioRepository;
        this.ufRepository = ufRepository;
    }


    public List<ResponseMunicipioDto> createMunicipio(String codigoUf, Municipio municipio) {

        var uf = this.ufRepository.findById(Integer.valueOf(codigoUf))
                .orElseThrow(() -> new ResponseStatusException(HttpStatusCode.valueOf(404), "Estado não exixste"));

        var newMunicipio = new Municipio();
        newMunicipio.setStatus(municipio.getStatus());
        newMunicipio.setNome(municipio.getNome());
        newMunicipio.setUf(uf);

        this.municipioRepository.save(newMunicipio);


        var allMunicipios = this.municipioRepository.findAll();

        return allMunicipios
                .stream()
                .map(municipios -> new ResponseMunicipioDto(
                        municipios.getCodigoMunicipio(),
                        municipios.getUf().getCodigoUf(),
                        municipios.getNome(),
                        municipios.getStatus(),
                        null
                )).toList();

    }

    public List<ResponseMunicipioDto> listAllMunicipio() {
        var allMunicipios = this.municipioRepository.findAll();

        return allMunicipios
                .stream()
                .map(municipio -> new ResponseMunicipioDto(
                        municipio.getCodigoMunicipio(),
                        municipio.getUf().getCodigoUf(),
                        municipio.getNome(),
                        municipio.getStatus(),
                        null
                )).toList();
    }

    public List<ResponseMunicipioDto> updateMunicipio(String codigoMunicipio, Municipio municipio) {

        var id = Integer.valueOf(codigoMunicipio);
        var municipioCurrent = this.municipioRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatusCode.valueOf(404), "municipio não exixste"));


        if (municipio.getNome() != null) {
            municipioCurrent.setNome(municipio.getNome());
        }

        if (municipio.getStatus() != null) {
            municipioCurrent.setStatus(municipio.getStatus());
        }

        this.municipioRepository.save(municipioCurrent);


        var allMunicipios = this.municipioRepository.findAll();

        return allMunicipios
                .stream()
                .map(municipios -> new ResponseMunicipioDto(
                        municipios.getCodigoMunicipio(),
                        municipios.getUf().getCodigoUf(),
                        municipios.getNome(),
                        municipios.getStatus(),
                        null
                )).toList();

    }
}