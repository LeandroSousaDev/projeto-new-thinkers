package com.leandroSS.new_thinkers.pessoa.dto.pessoa;

import com.leandroSS.new_thinkers.pessoa.dto.endereco.CreateEnderecoDto;

import java.util.List;

public record CreatePessoaDto(String nome,
                              String sobrenome,
                              Integer idade,
                              String login,
                              String senha,
                              Integer status,
                              List<CreateEnderecoDto> endereco) {
}
