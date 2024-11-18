package com.leandroSS.new_thinkers.controller;

import com.leandroSS.new_thinkers.dto.Bairro.CreateBairroDto;
import com.leandroSS.new_thinkers.dto.Bairro.ResponseBairroDto;
import com.leandroSS.new_thinkers.entity.Bairro;
import com.leandroSS.new_thinkers.service.BairroService;
import org.springframework.http.HttpStatusCode;
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

    @PostMapping("/")
    public ResponseEntity<List<ResponseBairroDto>> create(@RequestBody CreateBairroDto createBairroDto) {

        var newBairro = bairroService.createBairro(createBairroDto);
        return ResponseEntity.status(HttpStatusCode.valueOf(200)).body(newBairro);

    }

    // Rota de pesquisa de Municipio(pesquisar como fazer pesquisa por query em
    // Java)
    @GetMapping("/")
    public ResponseEntity<List<ResponseBairroDto>> readAll() {

        var bairros = this.bairroService.listAllBairro();
        return ResponseEntity.status(HttpStatusCode.valueOf(200)).body(bairros);
    }

    // se eu atualizar passando o id esta errado?
    @PutMapping("/{codigoBairro}")
    public ResponseEntity<List<ResponseBairroDto>> Update(@PathVariable("codigoBairro") String codigoBairro,
            @RequestBody Bairro bairro) {

        var updateList = this.bairroService.updateBairro(codigoBairro, bairro);
        return ResponseEntity.status(HttpStatusCode.valueOf(200)).body(updateList);
    }
}
