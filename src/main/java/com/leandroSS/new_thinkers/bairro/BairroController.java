package com.leandroSS.new_thinkers.bairro;

import com.leandroSS.new_thinkers.bairro.dto.CreateBairroDto;
import com.leandroSS.new_thinkers.bairro.dto.ResponseBairroDto;
import com.leandroSS.new_thinkers.bairro.dto.UpdateBairroDto;
import com.leandroSS.new_thinkers.utils.excepition.CustomException;

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
            throws CustomException {

        var newBairro = bairroService.createBairro(createBairroDto);
        return ResponseEntity.status(HttpStatusCode.valueOf(200)).body(newBairro);

    }

    @GetMapping("/")
    public ResponseEntity getBairro(
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) Integer status,
            @RequestParam(required = false) Integer codigoBairro,
            @RequestParam(required = false) String municipio) throws CustomException {

        List<ResponseBairroDto> bairros;

        if (nome != null) {
            bairros = this.bairroService.bairroByNome(nome);
            return ResponseEntity.status(HttpStatusCode.valueOf(200)).body(bairros);

        } else if (status != null) {
            bairros = this.bairroService.bairroByStatus(status);
            return ResponseEntity.status(HttpStatusCode.valueOf(200)).body(bairros);

        } else if (codigoBairro != null) {
            bairros = this.bairroService.bairroById(codigoBairro);
            if (bairros.isEmpty()) {
                return ResponseEntity.status(HttpStatusCode.valueOf(200)).body(bairros);
            }
            return ResponseEntity.status(HttpStatusCode.valueOf(200)).body(bairros.get(0));

        } else if (municipio != null) {
            bairros = this.bairroService.bairroByMunicipio(municipio);
            return ResponseEntity.status(HttpStatusCode.valueOf(200)).body(bairros);

        } else {
            bairros = this.bairroService.listAllBairro();
            return ResponseEntity.status(HttpStatusCode.valueOf(200)).body(bairros);
        }

    }

    @PutMapping("/")
    public ResponseEntity<List<ResponseBairroDto>> Update(@RequestBody UpdateBairroDto updateBairroDto)
            throws CustomException {

        var updateList = this.bairroService.updateBairro(updateBairroDto);
        return ResponseEntity.status(HttpStatusCode.valueOf(200)).body(updateList);
    }
}
