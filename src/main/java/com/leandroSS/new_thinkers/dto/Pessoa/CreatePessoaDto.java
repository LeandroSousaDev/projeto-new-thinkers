package com.leandroSS.new_thinkers.dto.Pessoa;

import com.leandroSS.new_thinkers.dto.Endereco.CreateEnderecoDto;

import java.util.List;

public record CreatePessoaDto(String nome,
                              String sobrenome,
                              Integer idade,
                              String login,
                              String senha,
                              Integer status,
                              List<CreateEnderecoDto> endereco) {
}
