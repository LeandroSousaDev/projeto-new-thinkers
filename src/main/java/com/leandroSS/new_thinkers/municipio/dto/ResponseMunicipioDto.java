package com.leandroSS.new_thinkers.municipio.dto;

import com.leandroSS.new_thinkers.UF.dto.ResponseUfDto;

public record ResponseMunicipioDto(Integer codigoMunicipio,
                                   Integer codigoUf,
                                   String nome,
                                   Integer status,
                                   ResponseUfDto uf) {
}
