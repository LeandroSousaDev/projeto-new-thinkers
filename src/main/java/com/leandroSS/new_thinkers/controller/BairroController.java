package com.leandroSS.new_thinkers.controller;

import com.leandroSS.new_thinkers.dto.ResponseBairroDto;
import com.leandroSS.new_thinkers.entity.Bairro;
import com.leandroSS.new_thinkers.entity.UF;
import com.leandroSS.new_thinkers.service.BairroService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bairro")
public class BairroController {
    private final BairroService bairroService;

    public BairroController(BairroService bairroService) {
        this.bairroService = bairroService;
    }


    //adicionar pesquisa se UF existe
    @PostMapping("/{codigoMunicipio}")
    public ResponseEntity<ResponseBairroDto> create(@PathVariable("codigoMunicipio") String codigoMunicipio,
                                                    @RequestBody Bairro bairro) {
        var newBairro = bairroService.createBairro(codigoMunicipio, bairro);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(newBairro);

    }

    //Rota de pesquisa de Municipio(pesquisar como fazer pesquisa por query em Java)
    @GetMapping("/")
    public ResponseEntity<List<ResponseBairroDto>> readAll() {
        var bairros = this.bairroService.listAllBairro();
        return ResponseEntity.ok(bairros);
    }

    //se eu atualizar passando o id esta errado?
    @PutMapping("/{codigoMunicipio}")
    public ResponseEntity<List<UF>> Update(@PathVariable("codigoBairro") String codigoBairro,
                                           @RequestBody Bairro bairro) {
        this.bairroService.updateBairro(codigoBairro, bairro);
        return ResponseEntity.noContent().build();
    }
}

