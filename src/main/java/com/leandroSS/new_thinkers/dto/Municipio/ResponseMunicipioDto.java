package com.leandroSS.new_thinkers.dto.Municipio;

import com.leandroSS.new_thinkers.dto.UF.ResponseUfDto;

public record ResponseMunicipioDto(Integer codigoMunicipio,
                                   Integer codigoUf,
                                   String nome,
                                   Integer status,
                                   ResponseUfDto uf) {
}
