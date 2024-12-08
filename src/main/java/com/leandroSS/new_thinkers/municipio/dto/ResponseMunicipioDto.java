package com.leandroSS.new_thinkers.municipio.dto;

import com.leandroSS.new_thinkers.UF.dto.ResponseUfDto;

public record ResponseMunicipioDto(Integer codigoMunicipio,
                                   Integer codigoUF,
                                   String nome,
                                   Integer status,
                                   ResponseUfDto UF) {
}
