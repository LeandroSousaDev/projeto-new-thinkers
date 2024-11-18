package com.leandroSS.new_thinkers.dto.Bairro;

import com.leandroSS.new_thinkers.dto.Municipio.ResponseMunicipioDto;

public record ResponseBairroDto(Integer codigoBairro,
                                Integer codigoMunicipio,
                                String nome,
                                Integer Status,
                                ResponseMunicipioDto municipio) {
}
