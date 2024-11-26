package com.leandroSS.new_thinkers.pessoa.dto.endereco;

public record CreateEnderecoDto(Integer codigoBairro,
                                String nomeRua,
                                String numero,
                                String complemento,
                                String cep) {
}
