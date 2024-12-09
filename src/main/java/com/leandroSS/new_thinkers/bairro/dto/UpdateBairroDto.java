package com.leandroSS.new_thinkers.bairro.dto;

import com.leandroSS.new_thinkers.municipio.dto.ResponseMunicipioDto;

public record UpdateBairroDto(Integer codigoBairro,
                Integer codigoMunicipio,
                String nome,
                Integer status,
                ResponseMunicipioDto municipio) {
}
