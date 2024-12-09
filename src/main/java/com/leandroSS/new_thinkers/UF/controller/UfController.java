package com.leandroSS.new_thinkers.UF.controller;

import com.leandroSS.new_thinkers.UF.service.UfService;
import com.leandroSS.new_thinkers.UF.dto.CreateUfDto;
import com.leandroSS.new_thinkers.UF.dto.ResponseUfDto;
import com.leandroSS.new_thinkers.UF.dto.UpdateUfDto;
import com.leandroSS.new_thinkers.excepition.CustomException;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/UF")
public class UfController {
    private final UfService ufService;

    public UfController(UfService ufService) {
        this.ufService = ufService;
    }

    @PostMapping("/")
    public ResponseEntity<List<ResponseUfDto>> create(@RequestBody CreateUfDto createUfDto) throws CustomException {

        var newUf = ufService.createUf(createUfDto);
        return ResponseEntity.status(HttpStatusCode.valueOf(200)).body(newUf);
    }

    @GetMapping("/")
    private ResponseEntity getUf(
            @RequestParam(required = false) String sigla,
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) Integer codigoUF,
            @RequestParam(required = false) Integer status) {

        List<ResponseUfDto> UFs;

        if (sigla != null) {
            UFs = this.ufService.ufBySigla(sigla);
            if (UFs.isEmpty()) {
                return ResponseEntity.status(HttpStatusCode.valueOf(200)).body(UFs);
            }
            return ResponseEntity.status(HttpStatusCode.valueOf(200)).body(UFs.get(0));

        } else if (nome != null) {
            UFs = this.ufService.ufByNome(nome);
            if (UFs.isEmpty()) {
                return ResponseEntity.status(HttpStatusCode.valueOf(200)).body(UFs);
            }
            return ResponseEntity.status(HttpStatusCode.valueOf(200)).body(UFs.get(0));

        } else if (codigoUF != null) {

            UFs = this.ufService.ufById(codigoUF);
            if (UFs.isEmpty()) {
                return ResponseEntity.status(HttpStatusCode.valueOf(200)).body(UFs);
            }
            return ResponseEntity.status(HttpStatusCode.valueOf(200)).body(UFs.get(0));

        } else if (status != null) {

            UFs = this.ufService.ufByStatus(status);
            return ResponseEntity.status(HttpStatusCode.valueOf(200)).body(UFs);
        } else {

            UFs = this.ufService.listAllUF();
            return ResponseEntity.status(HttpStatusCode.valueOf(200)).body(UFs);
        }

    }

    @PutMapping("/")
    public ResponseEntity<List<ResponseUfDto>> Update(@RequestBody UpdateUfDto updateUfDto) throws CustomException {

        var updatedList = this.ufService.updateUF(updateUfDto);
        return ResponseEntity.status(HttpStatusCode.valueOf(200)).body(updatedList);
    }

}