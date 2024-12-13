package com.leandroSS.new_thinkers.municipio.controller;

import com.leandroSS.new_thinkers.municipio.service.MunicipioService;
import com.leandroSS.new_thinkers.municipio.dto.CreateMunicipioDto;
import com.leandroSS.new_thinkers.municipio.dto.ResponseMunicipioDto;
import com.leandroSS.new_thinkers.municipio.dto.UpdateMunicipioDto;
import com.leandroSS.new_thinkers.excepition.CustomException;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/municipio")
public class MunicipioController {
    private final MunicipioService municipioService;

    public MunicipioController(MunicipioService municipioService) {
        this.municipioService = municipioService;
    }

    @PostMapping("/")
    public ResponseEntity<List<ResponseMunicipioDto>> create(@RequestBody CreateMunicipioDto createMunicipioDto)
            throws CustomException {

        var newMunicipio = municipioService.createMunicipio(createMunicipioDto);
        return ResponseEntity.status(HttpStatusCode.valueOf(200)).body(newMunicipio);
    }

    @GetMapping("/")
    public ResponseEntity getMunicipio(
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) Integer status,
            @RequestParam(required = false) Integer codigoMunicipio,
            @RequestParam(required = false) String codigoUF) throws CustomException {

        List<ResponseMunicipioDto> municipios;

        if (nome != null) {
            municipios = this.municipioService.municipioByNome(nome);
            return ResponseEntity.status(HttpStatusCode.valueOf(200)).body(municipios);

        } else if (status != null) {
            municipios = this.municipioService.municipioByStatus(status);
            return ResponseEntity.status(HttpStatusCode.valueOf(200)).body(municipios);

        } else if (codigoMunicipio != null) {
            municipios = this.municipioService.municipioById(codigoMunicipio);
            if (municipios.isEmpty()) {
                return ResponseEntity.status(HttpStatusCode.valueOf(200)).body(municipios);
            }
            return ResponseEntity.status(HttpStatusCode.valueOf(200)).body(municipios.get(0));

        } else if (codigoUF != null) {
            municipios = this.municipioService.municipuioByUF(codigoUF);
            return ResponseEntity.status(HttpStatusCode.valueOf(200)).body(municipios);

        } else {
            municipios = this.municipioService.listAllMunicipio();
            return ResponseEntity.status(HttpStatusCode.valueOf(200)).body(municipios);
        }

    }

    @PutMapping("/")
    public ResponseEntity<List<ResponseMunicipioDto>> UpdateUf(@RequestBody UpdateMunicipioDto updateMunicipioDto)
            throws CustomException {

        var updateList = this.municipioService.updateMunicipio(updateMunicipioDto);
        return ResponseEntity.status(HttpStatusCode.valueOf(200)).body(updateList);

    }
}
