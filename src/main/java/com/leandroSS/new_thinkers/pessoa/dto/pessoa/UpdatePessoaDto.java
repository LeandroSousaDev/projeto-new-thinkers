package com.leandroSS.new_thinkers.pessoa.dto.pessoa;

import java.util.List;

import com.leandroSS.new_thinkers.pessoa.dto.endereco.CreateEnderecoDto;

public record UpdatePessoaDto(Integer codigoPessoa,
                String nome,
                String sobrenome,
                Integer idade,
                String login,
                String senha,
                Integer status,
                List<CreateEnderecoDto> endereco) {
}
