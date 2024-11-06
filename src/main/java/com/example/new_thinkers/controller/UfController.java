package com.example.new_thinkers.controller;

import com.example.new_thinkers.entity.UF;
import com.example.new_thinkers.service.UfService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/uf")
public class UfController {

    private UfService ufService;

    public UfController(UfService ufService) {
        this.ufService = ufService;
    }

    @PostMapping("/")
    public ResponseEntity<UF> ceateUf(@RequestBody UF uf) {
        var newUf = ufService.createUf(uf);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(newUf);

    }
    //Rota de pesquisa de UF(pesquisar como fazer pesquisa por query em Java)
    @GetMapping("/")
    public ResponseEntity<List<UF>> readAll() {
        return null;
    }

    //se eu atualizar passando o id esta errado?
    @PutMapping("/")
    public ResponseEntity<List<UF>> UpdateUf() {
        return null;
    }
}
