package com.leandroSS.new_thinkers.utils.validation;

import com.leandroSS.new_thinkers.bairro.BairroEntity;
import com.leandroSS.new_thinkers.bairro.BairroRepository;
import com.leandroSS.new_thinkers.bairro.dto.CreateBairroDto;
import com.leandroSS.new_thinkers.bairro.dto.UpdateBairroDto;
import com.leandroSS.new_thinkers.utils.excepition.CustomException;

public class BairroValidation {

    public static void createValidation(CreateBairroDto dto, BairroRepository repository) throws CustomException {

        if (dto.codigoMunicipio() == null) {
            throw new CustomException("O campo codigo Municipio é obigatorio");
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

        var BairroNome = repository.findByNome(dto.nome());

        if (!BairroNome.isEmpty()) {
            throw new CustomException("Esse Bairro ja esta salvo");
        }

    }

    public static BairroEntity updateValidation(UpdateBairroDto dto, BairroRepository repository)
            throws CustomException {

        if (dto.codigoMunicipio() == null) {
            throw new CustomException("O campo codigo Municipio é obigatorio");
        }

        if (dto.codigoBairro() == null) {
            throw new CustomException("O campo codigo Bairro é obigatorio");
        }

        var id = dto.codigoBairro();
        var bairroCurrent = repository.findByCodigoBairro(id);

        if (bairroCurrent.isEmpty()) {
            throw new CustomException("bairro não encontrado");
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

        return bairroCurrent.get(0);

    }

}
