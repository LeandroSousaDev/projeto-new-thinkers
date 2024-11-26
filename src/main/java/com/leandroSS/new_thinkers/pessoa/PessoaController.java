package com.leandroSS.new_thinkers.pessoa;

import com.leandroSS.new_thinkers.pessoa.dto.pessoa.CreatePessoaDto;
import com.leandroSS.new_thinkers.pessoa.dto.pessoa.ResponsePessoaDto;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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
    private ResponseEntity getPessoa(
            @RequestParam(required = false) Integer codigoPessoa,
            @RequestParam(required = false) String login,
            @RequestParam(required = false) Integer status) {

        ResponsePessoaDto pessoa;
        List<ResponsePessoaDto> pessoas;

        if (codigoPessoa != null) {
            pessoa = this.pessoaService.pessoaById(codigoPessoa);
            return ResponseEntity.status(HttpStatusCode.valueOf(200)).body(pessoa);

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

    @PutMapping("/{codigoPessoa}")
    public ResponseEntity<ResponsePessoaDto> update(@PathVariable("codigoPessoa") String codigoPessoa,
            @RequestBody CreatePessoaDto createPessoaDto) {
        var updatePessoa = this.pessoaService.uptatePessoa(codigoPessoa, createPessoaDto);
        return ResponseEntity.status(HttpStatusCode.valueOf(200)).body(updatePessoa);
    }

}