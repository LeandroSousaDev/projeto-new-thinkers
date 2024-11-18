package com.leandroSS.new_thinkers.dto.Pessoa;

import com.leandroSS.new_thinkers.dto.Endereco.ResponseEnderecoDto;

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
