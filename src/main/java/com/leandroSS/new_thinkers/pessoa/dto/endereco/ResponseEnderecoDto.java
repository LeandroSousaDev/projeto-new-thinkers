package com.leandroSS.new_thinkers.pessoa.dto.endereco;

import com.leandroSS.new_thinkers.bairro.dto.ResponseBairroDto;

public record ResponseEnderecoDto(Integer codigoEndereço,
                                  Integer codigoPessoa,
                                  Integer codigoBairro,
                                  String nomeRua,
                                  String numero,
                                  String complemento,
                                  String cep,
                                  ResponseBairroDto bairro) {
}
