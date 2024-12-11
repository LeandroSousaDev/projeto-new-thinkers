package com.leandroSS.new_thinkers.pessoa.controller;

import com.leandroSS.new_thinkers.pessoa.dto.pessoa.UpdatePessoaDto;
import com.leandroSS.new_thinkers.pessoa.service.PessoaService;
import com.leandroSS.new_thinkers.pessoa.dto.pessoa.CreatePessoaDto;
import com.leandroSS.new_thinkers.pessoa.dto.pessoa.ResponsePessoaDto;
import com.leandroSS.new_thinkers.excepition.CustomException;

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
    public ResponseEntity<List<ResponsePessoaDto>> create(@RequestBody CreatePessoaDto createPessoaDto)
            throws CustomException {
        var newPessoa = this.pessoaService.createPessoa(createPessoaDto);
        return ResponseEntity.status(HttpStatusCode.valueOf(200)).body(newPessoa);
    }

    @GetMapping("/")
    public ResponseEntity getPessoa(
            @RequestParam(required = false) Integer codigoPessoa,
            @RequestParam(required = false) String login,
            @RequestParam(required = false) Integer status) {

        List<ResponsePessoaDto> pessoas;

        if (codigoPessoa != null) {
            pessoas = this.pessoaService.pessoaById(codigoPessoa);
            if (pessoas.isEmpty()) {
                return ResponseEntity.status(HttpStatusCode.valueOf(200)).body(pessoas);
            }
            return ResponseEntity.status(HttpStatusCode.valueOf(200)).body(pessoas.get(0));

        } else if (login != null) {
            pessoas = this.pessoaService.pessoaByLogin(login);
            return ResponseEntity.status(HttpStatusCode.valueOf(200)).body(pessoas);

        } else if (status != null) {
            pessoas = this.pessoaService.pessoaByStatus(status);
            return ResponseEntity.status(HttpStatusCode.valueOf(200)).body(pessoas);

        } else {
            pessoas = this.pessoaService.listAllPessoas();
            return ResponseEntity.status(HttpStatusCode.valueOf(200)).body(pessoas);
        }
    }

    @PutMapping("/")
    public ResponseEntity<List<ResponsePessoaDto>> update(@RequestBody UpdatePessoaDto updatePessoaDto)
            throws CustomException {
        var updatePessoa = this.pessoaService.uptatePessoa(updatePessoaDto);
        return ResponseEntity.status(HttpStatusCode.valueOf(200)).body(updatePessoa);
    }

}