package com.leandroSS.new_thinkers.pessoa.validation;

import com.leandroSS.new_thinkers.bairro.repository.BairroRepository;
import com.leandroSS.new_thinkers.excepition.CustomException;
import com.leandroSS.new_thinkers.pessoa.dto.endereco.CreateEnderecoDto;
import com.leandroSS.new_thinkers.pessoa.dto.pessoa.CreatePessoaDto;
import com.leandroSS.new_thinkers.pessoa.dto.pessoa.UpdatePessoaDto;
import com.leandroSS.new_thinkers.pessoa.entity.PessoaEntity;
import com.leandroSS.new_thinkers.pessoa.repository.PessoaRepository;

public class PessoaValidation {
    public static void createValidation(CreatePessoaDto dto,
            PessoaRepository repository,
            BairroRepository repository2) throws CustomException {

        if (dto.nome() == null || dto.nome().isBlank()) {
            throw new CustomException("O campo nome é obigatorio");
        }

        if (dto.sobrenome() == null || dto.sobrenome().isBlank()) {
            throw new CustomException("O campo sobrenome é obigatorio");
        }

        if (dto.idade() == null) {
            throw new CustomException("O campo idade é obigatorio");
        }

        if (dto.login() == null || dto.login().isBlank()) {
            throw new CustomException("O campo login é obigatorio");
        }

        var pessoaLogin = repository.findByLogin(dto.login());

        if (!pessoaLogin.isEmpty()) {
            throw new CustomException("Esse Login esta sendo usado por outro usuario");
        }

        if (dto.senha() == null || dto.senha().isBlank()) {
            throw new CustomException("O campo senha é obigatorio");
        }

        if (dto.status() == null) {
            throw new CustomException("O campo status é obigatorio");
        }

        if (dto.status() <= 0 || dto.status() > 2) {
            throw new CustomException("valor do campo status é invalido: use 1 para ativo e 2 para inativo");
        }

        if (dto.endereco().isEmpty()) {
            throw new CustomException("Voce deve adicionar pelo menos um endereço para a pessoa");
        }

        for (CreateEnderecoDto endereco : dto.endereco()) {

            if (endereco.nomeRua() == null || endereco.nomeRua().isBlank()) {
                throw new CustomException("O campo nome Rua é obigatorio");
            }

            if (endereco.numero() == null || endereco.numero().isBlank()) {
                throw new CustomException("O campo numero é obigatorio");
            }

            if (endereco.complemento() == null || endereco.complemento().isBlank()) {
                throw new CustomException("O campo complemento é obigatorio");
            }

            if (endereco.cep() == null || endereco.cep().isBlank()) {
                throw new CustomException("O campo CEP é obigatorio");
            }

            if (endereco.codigoBairro() == null) {
                throw new CustomException("O campo codigo bairro é obigatorio");
            }

            var bairroCurrent = repository2.findByCodigoBairro(endereco.codigoBairro());

            if (bairroCurrent.isEmpty()) {
                throw new CustomException("Bairro não encontrado");
            }
        }
    }

    public static PessoaEntity updateValidation(UpdatePessoaDto dto,
            PessoaRepository repository,
            BairroRepository repository2)
            throws CustomException {

        if (dto.codigoPessoa() == null) {
            throw new CustomException("O campo codigo pessoa é obigatorio");
        }

        if (dto.nome() == null || dto.nome().isBlank()) {
            throw new CustomException("O campo nome é obigatorio");
        }

        if (dto.sobrenome() == null || dto.sobrenome().isBlank()) {
            throw new CustomException("O campo sobrenome é obigatorio");
        }

        if (dto.idade() == null) {
            throw new CustomException("O campo idade é obigatorio");
        }

        if (dto.login() == null || dto.login().isBlank()) {
            throw new CustomException("O campo login é obigatorio");
        }

        if (dto.senha() == null || dto.senha().isBlank()) {
            throw new CustomException("O campo senha é obigatorio");
        }

        if (dto.status() == null) {
            throw new CustomException("O campo status é obigatorio");
        }

        if (dto.status() <= 0 || dto.status() > 2) {
            throw new CustomException("valor do campo status é invalido: use 1 para ativo e 2 para inativo");
        }

        if (dto.endereco().isEmpty()) {
            throw new CustomException("Voce deve adicionar pelo menos um endereço para a pessoa");
        }

        var id = dto.codigoPessoa();
        var pessoaCurrent = repository.findByCodigoPessoa(id);

        if (pessoaCurrent.isEmpty()) {
            throw new CustomException("Pessoa não encontrado");
        }

        for (CreateEnderecoDto endereco : dto.endereco()) {

            if (endereco.nomeRua() == null || endereco.nomeRua().isBlank()) {
                throw new CustomException("O campo nome Rua é obigatorio");
            }

            if (endereco.numero() == null || endereco.numero().isBlank()) {
                throw new CustomException("O campo numero é obigatorio");
            }

            if (endereco.complemento() == null || endereco.complemento().isBlank()) {
                throw new CustomException("O campo complemento é obigatorio");
            }

            if (endereco.cep() == null || endereco.cep().isBlank()) {
                throw new CustomException("O campo CEP é obigatorio");
            }

            if (endereco.codigoBairro() == null) {
                throw new CustomException("O campo codigo bairro é obigatorio");
            }

            var bairroCurrent = repository2.findByCodigoBairro(endereco.codigoBairro());

            if (bairroCurrent.isEmpty()) {
                throw new CustomException("Bairro não encontrado");
            }
        }

        return pessoaCurrent.get(0);
    }
}
