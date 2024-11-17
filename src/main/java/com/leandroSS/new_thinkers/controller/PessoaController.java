package com.leandroSS.new_thinkers.controller;

import com.leandroSS.new_thinkers.dto.CreatePessoaDto;
import com.leandroSS.new_thinkers.dto.ResponsePessoaDto;
import com.leandroSS.new_thinkers.service.PessoaService;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pessoa")
public class PessoaController {
    private final PessoaService pessoaService;

    public PessoaController(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }

    @PostMapping("/")
    public ResponseEntity<ResponsePessoaDto> create(@RequestBody CreatePessoaDto createPessoaDto) {

        var newPessoa = this.pessoaService.createPessoa(createPessoaDto);
        return ResponseEntity.status(HttpStatusCode.valueOf(200)).body(newPessoa);
    }
}
