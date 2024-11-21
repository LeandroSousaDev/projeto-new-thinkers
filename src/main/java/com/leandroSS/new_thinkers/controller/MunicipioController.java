package com.leandroSS.new_thinkers.controller;

import com.leandroSS.new_thinkers.dto.Municipio.CreateMunicipioDto;
import com.leandroSS.new_thinkers.dto.Municipio.ResponseMunicipioDto;
import com.leandroSS.new_thinkers.entity.Municipio;
import com.leandroSS.new_thinkers.entity.UF;
import com.leandroSS.new_thinkers.service.MunicipioService;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/municipio")
public class MunicipioController {
    private final MunicipioService municipioService;

    public MunicipioController(MunicipioService municipioService) {
        this.municipioService = municipioService;
    }

    @PostMapping("/")
    public ResponseEntity<List<ResponseMunicipioDto>> create(@RequestBody CreateMunicipioDto createMunicipioDto) {

        var newMunicipio = municipioService.createMunicipio(createMunicipioDto);

        return ResponseEntity.status(HttpStatusCode.valueOf(200)).body(newMunicipio);

    }

    // Rota de pesquisa de Municipio(pesquisar como fazer pesquisa por query em
    // Java)
    @GetMapping("/")
    public ResponseEntity<List<ResponseMunicipioDto>> readAll() {
        var municipios = this.municipioService.listAllMunicipio();
        return ResponseEntity.status(HttpStatusCode.valueOf(200)).body(municipios);
    }

    @GetMapping("/get/")
    private ResponseEntity getUf(
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) Integer status,
            @RequestParam(required = false) Integer codigoMunicipio,
            @RequestParam(required = false) String uf) {

        ResponseMunicipioDto municipio;
        List<ResponseMunicipioDto> municipios;

        if (nome != null) {
            municipios = this.municipioService.municipioByNome(nome);
            return ResponseEntity.status(HttpStatusCode.valueOf(200)).body(municipios);

        } else if (status != null) {
            municipios = this.municipioService.municipioByStatus(status);
            return ResponseEntity.status(HttpStatusCode.valueOf(200)).body(municipios);

        } else if (codigoMunicipio != null) {
            municipio = this.municipioService.municipioById(codigoMunicipio);
            return ResponseEntity.status(HttpStatusCode.valueOf(200)).body(municipio);

        } else if (uf != null) {
            municipios = this.municipioService.municipuioByUF(uf);
            return ResponseEntity.status(HttpStatusCode.valueOf(200)).body(municipios);

        } else {
            throw new ResponseStatusException(HttpStatusCode.valueOf(404), "voce fez uma pesquisa nula");

        }

    }

    @PutMapping("/{codigoMunicipio}")
    public ResponseEntity<List<ResponseMunicipioDto>> UpdateUf(@PathVariable("codigoMunicipio") String codigoMunicipio,
            @RequestBody Municipio municipio) {

        var updateList = this.municipioService.updateMunicipio(codigoMunicipio, municipio);
        return ResponseEntity.status(HttpStatusCode.valueOf(200)).body(updateList);

    }
}
