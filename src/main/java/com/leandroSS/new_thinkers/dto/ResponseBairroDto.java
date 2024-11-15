package com.leandroSS.new_thinkers.dto;

public record ResponseBairroDto(Integer codigoBairro,
                                Integer codigoMunicipio,
                                String nome,
                                Integer Status,
                                ResponseMunicipioDto municipio) {
}
