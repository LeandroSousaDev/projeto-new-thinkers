package com.leandroSS.new_thinkers.controller;

import com.leandroSS.new_thinkers.dto.Pessoa.CreatePessoaDto;
import com.leandroSS.new_thinkers.dto.Pessoa.ResponsePessoaDto;
import com.leandroSS.new_thinkers.service.PessoaService;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/")
    public ResponseEntity<List<ResponsePessoaDto>> readAll() {

        var allPessoas = this.pessoaService.listAllPessoas();
        return ResponseEntity.status(HttpStatusCode.valueOf(200)).body(allPessoas);
    }

    @PutMapping("/{codigoPessoa}")
    public ResponseEntity<ResponsePessoaDto> update(@PathVariable("codigoPessoa") String codigoPessoa,
                                                    @RequestBody CreatePessoaDto createPessoaDto) {
        var updatePessoa = this.pessoaService.uptatePessoa(codigoPessoa, createPessoaDto);
        return ResponseEntity.status(HttpStatusCode.valueOf(200)).body(updatePessoa);
    }
}
