package com.leandroSS.new_thinkers.dto;

public record ResponseEnderecoDto(Integer codigoEndereço,
                                  Integer codigoPessoa,
                                  Integer codigoBairro,
                                  String nomeRua,
                                  String numero,
                                  String complemento,
                                  String cep,
                                  ResponseBairroDto bairro) {
}
