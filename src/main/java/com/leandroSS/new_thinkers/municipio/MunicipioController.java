package com.leandroSS.new_thinkers.municipio;

import com.leandroSS.new_thinkers.municipio.dto.CreateMunicipioDto;
import com.leandroSS.new_thinkers.municipio.dto.ResponseMunicipioDto;
import com.leandroSS.new_thinkers.utils.excepition.NotFoundException;

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
            throws NotFoundException {

        var newMunicipio = municipioService.createMunicipio(createMunicipioDto);
        return ResponseEntity.status(HttpStatusCode.valueOf(200)).body(newMunicipio);
    }

    @GetMapping("/")
    public ResponseEntity getMunicipio(
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) Integer status,
            @RequestParam(required = false) Integer codigoMunicipio,
            @RequestParam(required = false) String uf) throws NotFoundException {

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
            municipios = this.municipioService.listAllMunicipio();
            return ResponseEntity.status(HttpStatusCode.valueOf(200)).body(municipios);
        }

    }

    @PutMapping("/{codigoMunicipio}")
    public ResponseEntity<List<ResponseMunicipioDto>> UpdateUf(@PathVariable("codigoMunicipio") String codigoMunicipio,
            @RequestBody CreateMunicipioDto createMunicipioDto) throws NotFoundException {

        var updateList = this.municipioService.updateMunicipio(codigoMunicipio, createMunicipioDto);
        return ResponseEntity.status(HttpStatusCode.valueOf(200)).body(updateList);

    }
}
