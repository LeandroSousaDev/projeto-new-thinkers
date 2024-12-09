package com.leandroSS.new_thinkers.utils.validation;

import com.leandroSS.new_thinkers.UF.UfEntity;
import com.leandroSS.new_thinkers.UF.UfRepository;
import com.leandroSS.new_thinkers.UF.dto.CreateUfDto;
import com.leandroSS.new_thinkers.UF.dto.UpdateUfDto;
import com.leandroSS.new_thinkers.utils.excepition.CustomException;

public class UfValidation {

    public static void createValidation(CreateUfDto dto, UfRepository repository) throws CustomException {
        if (dto.nome() == null || dto.sigla().isBlank()) {
            throw new CustomException("O campo sigla é obigatorio");
        }

        if (dto.nome().isBlank()) {
            throw new CustomException("O campo nome é obigatorio");
        }

        if (dto.status() == null) {
            throw new CustomException("O campo status é obigatorio");
        }

        if (dto.status() <= 0 || dto.status() > 2) {
            throw new CustomException("valor do campo status é invalido: use 1 para ativo e 2 para inativo");
        }

        var ufSigla = repository.findBySigla(dto.sigla());

        var ufNome = repository.findByNome(dto.nome());

        if (!ufSigla.isEmpty() || !ufNome.isEmpty()) {
            throw new CustomException("Esse Estado ja esta salvo");
        }

    }

    public static UfEntity updateValidation(UpdateUfDto dto, UfRepository repository) throws CustomException {

        if (dto.codigoUF() == null) {
            throw new CustomException("O campo codigoUF é obigatorio");
        }

        var id = dto.codigoUF();
        var ufCurrent = repository.findByCodigoUF(id);

        if (ufCurrent == null) {
            throw new CustomException("Estado não encontrado");
        }

        if (dto.sigla().isBlank()) {
            throw new CustomException("O campo sigla é obigatorio");
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

        return ufCurrent.get(0);

    }

}
