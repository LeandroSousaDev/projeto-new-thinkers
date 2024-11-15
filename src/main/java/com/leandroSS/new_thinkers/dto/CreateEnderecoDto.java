package com.leandroSS.new_thinkers.dto;

public record CreateEnderecoDto(Integer codigoBairro,
                                String nomeRua,
                                String numero,
                                String completo,
                                String cep) {
}
