package com.leandroSS.new_thinkers.bairro;

import com.leandroSS.new_thinkers.bairro.dto.CreateBairroDto;
import com.leandroSS.new_thinkers.bairro.dto.ResponseBairroDto;
import com.leandroSS.new_thinkers.utils.excepition.NotFoundException;

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
    public ResponseEntity<List<ResponseBairroDto>> create(@RequestBody CreateBairroDto createBairroDto)
            throws NotFoundException {

        var newBairro = bairroService.createBairro(createBairroDto);
        return ResponseEntity.status(HttpStatusCode.valueOf(200)).body(newBairro);

    }

    @GetMapping("/")
    public ResponseEntity getBairro(
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) Integer status,
            @RequestParam(required = false) Integer codigoBairro,
            @RequestParam(required = false) String municipio) throws NotFoundException {

        ResponseBairroDto bairro;
        List<ResponseBairroDto> bairros;

        if (nome != null) {
            bairros = this.bairroService.bairroByNome(nome);
            return ResponseEntity.status(HttpStatusCode.valueOf(200)).body(bairros);

        } else if (status != null) {
            bairros = this.bairroService.bairroByStatus(status);
            return ResponseEntity.status(HttpStatusCode.valueOf(200)).body(bairros);

        } else if (codigoBairro != null) {
            bairro = this.bairroService.bairroById(codigoBairro);
            return ResponseEntity.status(HttpStatusCode.valueOf(200)).body(bairro);

        } else if (municipio != null) {
            bairros = this.bairroService.bairroByMunicipio(municipio);
            return ResponseEntity.status(HttpStatusCode.valueOf(200)).body(bairros);

        } else {
            bairros = this.bairroService.listAllBairro();
            return ResponseEntity.status(HttpStatusCode.valueOf(200)).body(bairros);
        }

    }

    @PutMapping("/{codigoBairro}")
    public ResponseEntity<List<ResponseBairroDto>> Update(@PathVariable("codigoBairro") String codigoBairro,
            @RequestBody CreateBairroDto createBairroDto) throws NotFoundException {

        var updateList = this.bairroService.updateBairro(codigoBairro, createBairroDto);
        return ResponseEntity.status(HttpStatusCode.valueOf(200)).body(updateList);
    }
}
