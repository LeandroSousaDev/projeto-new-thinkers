package com.leandroSS.new_thinkers.utils.validation;

import com.leandroSS.new_thinkers.UF.dto.CreateUfDto;
import com.leandroSS.new_thinkers.utils.excepition.CustomException;

public class UfValidation {

    public static void validarNull(CreateUfDto objeto) throws CustomException {
        if (objeto.sigla().isBlank()) {
            throw new CustomException("O campo sigla é obigatorio");
        }

        if (objeto.nome().isBlank()) {
            throw new CustomException("O campo nome é obigatorio");
        }

        if (objeto.status() == null) {
            throw new CustomException("O campo status é obigatorio");
        }

        if (objeto.status() <= 0 || objeto.status() > 2) {
            throw new CustomException("valor do campo status é invalido: use 1 para ativo e 2 para inativo");
        }

    }
}
