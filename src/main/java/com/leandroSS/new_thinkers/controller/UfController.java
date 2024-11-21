package com.leandroSS.new_thinkers.controller;

import com.leandroSS.new_thinkers.dto.UF.CreateUfDto;
import com.leandroSS.new_thinkers.dto.UF.ResponseUfDto;
import com.leandroSS.new_thinkers.entity.UF;
import com.leandroSS.new_thinkers.service.UfService;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/uf")
public class UfController {
    private final UfService ufService;

    public UfController(UfService ufService) {
        this.ufService = ufService;
    }

    // adiciona verificação para UF repedidas
    // adicionar sigla so deve ter 2 caracteres
    // adiciona função que converte para maiuscula
    @PostMapping("/")
    public ResponseEntity<List<ResponseUfDto>> create(@RequestBody CreateUfDto createUfDto) {

        var newUf = ufService.createUf(createUfDto);
        return ResponseEntity.status(HttpStatusCode.valueOf(200)).body(newUf);
    }

    // Rota de pesquisa de UF(pesquisar como fazer pesquisa por query em Java)
    @GetMapping("/")
    public ResponseEntity<List<ResponseUfDto>> readAll() {

        var UFs = this.ufService.listAllUF();
        return ResponseEntity.status(HttpStatusCode.valueOf(200)).body(UFs);
    }

    @GetMapping("/get/")
    private ResponseEntity listUfBySigla(
            @RequestParam(required = false) String sigla,
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) Integer codigoUf,
            @RequestParam(required = false) Integer status) {

        ResponseUfDto UF;
        List<ResponseUfDto> UFs;

        if (sigla != null) {

            UF = this.ufService.ufBySigla(sigla);
            return ResponseEntity.status(HttpStatusCode.valueOf(200)).body(UF);

        } else if (nome != null) {

            UF = this.ufService.ufByNome(nome);
            return ResponseEntity.status(HttpStatusCode.valueOf(200)).body(UF);

        } else if (codigoUf != null) {

            UF = this.ufService.ufById(codigoUf);
            return ResponseEntity.status(HttpStatusCode.valueOf(200)).body(UF);

        } else if (status != null) {

            UFs = this.ufService.ufByStatus(status);
            return ResponseEntity.status(HttpStatusCode.valueOf(200)).body(UFs);
        } else {
            throw new ResponseStatusException(HttpStatusCode.valueOf(404), "voce fez uma pesquisa nula");
        }

    }

    @PutMapping("/{codigo_uf}")
    public ResponseEntity<List<ResponseUfDto>> Update(@PathVariable("codigo_uf") String codigoUf, @RequestBody UF uf) {

        var updateList = this.ufService.updateUF(codigoUf, uf);
        return ResponseEntity.status(HttpStatusCode.valueOf(200)).body(updateList);
    }
}