package com.leandroSS.new_thinkers.pessoa.dto.pessoa;

import com.leandroSS.new_thinkers.pessoa.dto.endereco.ResponseEnderecoDto;

import java.util.List;

public record ResponsePessoaDto(Integer codigoPessoa,
                                String nome,
                                String sobrenome,
                                Integer idade,
                                String login,
                                String senha,
                                Integer status,
                                List<ResponseEnderecoDto> endereço) {
}
