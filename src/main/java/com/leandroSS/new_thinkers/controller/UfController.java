package com.leandroSS.new_thinkers.controller;

import com.leandroSS.new_thinkers.dto.ResponseUfDto;
import com.leandroSS.new_thinkers.entity.UF;
import com.leandroSS.new_thinkers.service.UfService;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/uf")
public class UfController {
    private final UfService ufService;
    public UfController(UfService ufService) {
        this.ufService = ufService;
    }

    //adiciona verificação para UF repedidas
    // adicionar sigla so deve ter 2 caracteres
    //adiciona função que converte para maiuscula
    @PostMapping("/")
    public ResponseEntity<List<ResponseUfDto>> create(@RequestBody UF uf) {

        var newUf = ufService.createUf(uf);
        return ResponseEntity.status(HttpStatusCode.valueOf(200)).body(newUf);
    }

    //Rota de pesquisa de UF(pesquisar como fazer pesquisa por query em Java)
    @GetMapping("/")
    public ResponseEntity<List<ResponseUfDto>> readAll() {

        var UFs = this.ufService.listAllUF();
        return ResponseEntity.status(HttpStatusCode.valueOf(200)).body(UFs);
    }

    @PutMapping("/{codigo_uf}")
    public ResponseEntity<List<ResponseUfDto>> Update(@PathVariable("codigo_uf") String codigoUf, @RequestBody UF uf) {

        var updateList = this.ufService.updateUF(codigoUf, uf);
        return ResponseEntity.status(HttpStatusCode.valueOf(200)).body(updateList);
    }
}