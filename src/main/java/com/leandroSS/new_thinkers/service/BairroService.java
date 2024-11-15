package com.leandroSS.new_thinkers.service;

import com.leandroSS.new_thinkers.dto.ResponseBairroDto;
import com.leandroSS.new_thinkers.entity.Bairro;
import com.leandroSS.new_thinkers.repository.BairroRepository;
import com.leandroSS.new_thinkers.repository.MunicipioRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class BairroService {
    private final BairroRepository bairroRepository;
    private final MunicipioRepository municipioRepository;

    public BairroService(BairroRepository bairroRepository, MunicipioRepository municipioRepository) {
        this.bairroRepository = bairroRepository;
        this.municipioRepository = municipioRepository;
    }


    public ResponseBairroDto createBairro(String codigoMunicipio, Bairro bairro) {

        var municipio = this.municipioRepository.findById(Integer.valueOf(codigoMunicipio))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Municipio não exixste"));

        var newBairro = new Bairro();
        newBairro.setStatus(municipio.getStatus());
        newBairro.setNome(municipio.getNome());
        newBairro.setMunicipio(municipio);

        this.bairroRepository.save(newBairro);

        return new ResponseBairroDto(
                newBairro.getCodigoBairro(),
                newBairro.getMunicipio().getCodigoMunicipio(),
                newBairro.getNome(),
                newBairro.getStatus(),
                null
        );
    }

    public List<ResponseBairroDto> listAllBairro() {
        var allBairro = this.bairroRepository.findAll();

        return allBairro
                .stream()
                .map(bairro -> new ResponseBairroDto(
                        bairro.getCodigoBairro(),
                        bairro.getMunicipio().getCodigoMunicipio(),
                        bairro.getNome(),
                        bairro.getStatus(),
                        null
                )).toList();
    }

    public void updateBairro(String codigoBairro, Bairro bairro) {

        var id = Integer.valueOf(codigoBairro);
        var BairroEntity = this.bairroRepository.findById(id);

        if (BairroEntity.isPresent()) {
            var municipioCurrent = BairroEntity.get();

            if (bairro.getNome() != null) {
                municipioCurrent.setNome(bairro.getNome());
            }

            if (bairro.getStatus() != null) {
                municipioCurrent.setStatus(bairro.getStatus());
            }

            this.bairroRepository.save(municipioCurrent);
        }

    }
}