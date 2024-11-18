package com.leandroSS.new_thinkers.dto.Endereco;

public record CreateEnderecoDto(Integer codigoBairro,
                                String nomeRua,
                                String numero,
                                String complemento,
                                String cep) {
}
