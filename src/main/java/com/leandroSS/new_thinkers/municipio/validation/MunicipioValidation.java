package com.leandroSS.new_thinkers.municipio.validation;

import com.leandroSS.new_thinkers.municipio.entity.MunicipioEntity;
import com.leandroSS.new_thinkers.municipio.repository.MunicipioRepository;
import com.leandroSS.new_thinkers.municipio.dto.CreateMunicipioDto;
import com.leandroSS.new_thinkers.municipio.dto.UpdateMunicipioDto;
import com.leandroSS.new_thinkers.excepition.CustomException;

public class MunicipioValidation {

    public static void createValidation(CreateMunicipioDto dto, MunicipioRepository repository) throws CustomException {

        if (dto.codigoUF() == null) {
            throw new CustomException("O campo codigo UF é obigatorio");

        }

        if (dto.nome() == null || dto.nome().isBlank()) {
            throw new CustomException("O campo nome é obigatorio");
        }

        if (dto.status() == null) {
            throw new CustomException("O campo status é obigatorio");
        }

        if (dto.status() <= 0 || dto.status() > 2) {
            throw new CustomException("valor do campo status é invalido: use 1 para ativo e 2 para inativo");
        }

        var MunicipioNome = repository.findByNome(dto.nome());

        if (!MunicipioNome.isEmpty()) {
            throw new CustomException("Esse Municipio ja esta salvo");
        }

    }

    public static MunicipioEntity updateValidation(UpdateMunicipioDto dto, MunicipioRepository repository)
            throws CustomException {

        if (dto.codigoUF() == null) {
            throw new CustomException("O campo codigo UF é obigatorio");
        }

        if (dto.codigoMunicipio() == null) {
            throw new CustomException("O campo codigo Municipio é obigatorio");
        }

        var id = dto.codigoMunicipio();
        var municipioCurrent = repository.findByCodigoMunicipio(id);

        if (municipioCurrent == null) {
            throw new CustomException("Municipio não encontrado");
        }

        if (dto.nome() == null || dto.nome().isBlank()) {
            throw new CustomException("O campo nome é obigatorio");
        }

        if (dto.status() == null) {
            throw new CustomException("O campo status é obigatorio");
        }

        if (dto.status() <= 0 || dto.status() > 2) {
            throw new CustomException("valor do campo status é invalido: use 1 para ativo e 2 para inativo");
        }

        return municipioCurrent.get(0);

    }

}
