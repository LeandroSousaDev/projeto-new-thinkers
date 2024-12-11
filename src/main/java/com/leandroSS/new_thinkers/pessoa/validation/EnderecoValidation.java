package com.leandroSS.new_thinkers.pessoa.validation;

import com.leandroSS.new_thinkers.excepition.CustomException;
import com.leandroSS.new_thinkers.pessoa.dto.endereco.CreateEnderecoDto;

public class EnderecoValidation {
    public static void createValidation(CreateEnderecoDto dto) throws CustomException {
        if (dto.nomeRua() == null || dto.nomeRua().isBlank()) {
            throw new CustomException("O campo nome Rua é obigatorio");
        }

        if (dto.numero() == null || dto.numero().isBlank()) {
            throw new CustomException("O campo numero é obigatorio");
        }

        if (dto.complemento() == null || dto.complemento().isBlank()) {
            throw new CustomException("O campo complemento é obigatorio");
        }

        if (dto.cep() == null || dto.cep().isBlank()) {
            throw new CustomException("O campo CEP é obigatorio");
        }

        if (dto.codigoBairro() == null) {
            throw new CustomException("O campo codigo bairro é obigatorio");
        }

    }
}
