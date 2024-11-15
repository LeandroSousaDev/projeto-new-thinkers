package com.leandroSS.new_thinkers.dto;

public record ResponseMunicipioDto(Integer codigoMunicipio,
                                   Integer codigoUf,
                                   String nome,
                                   Integer status,
                                   ResponseUfDto uf) {
}
