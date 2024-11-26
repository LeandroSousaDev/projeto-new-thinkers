package com.leandroSS.new_thinkers.bairro.dto;

import com.leandroSS.new_thinkers.municipio.dto.ResponseMunicipioDto;

public record ResponseBairroDto(Integer codigoBairro,
                                Integer codigoMunicipio,
                                String nome,
                                Integer Status,
                                ResponseMunicipioDto municipio) {
}
