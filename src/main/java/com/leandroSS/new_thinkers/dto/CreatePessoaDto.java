package com.leandroSS.new_thinkers.dto;

import java.util.List;

public record CreatePessoaDto(String nome,
                              String sobrenome,
                              Integer idade,
                              String login,
                              String senha,
                              Integer status,
                              List<CreateEnderecoDto> endereco) {
}
