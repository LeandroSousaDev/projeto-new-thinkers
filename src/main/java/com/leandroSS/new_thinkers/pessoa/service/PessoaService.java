package com.leandroSS.new_thinkers.pessoa.service;

import java.util.ArrayList;
import java.util.List;

import com.leandroSS.new_thinkers.pessoa.dto.pessoa.UpdatePessoaDto;
import com.leandroSS.new_thinkers.pessoa.validation.PessoaValidation;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.leandroSS.new_thinkers.UF.dto.ResponseUfDto;
import com.leandroSS.new_thinkers.bairro.repository.BairroRepository;
import com.leandroSS.new_thinkers.bairro.dto.ResponseBairroDto;
import com.leandroSS.new_thinkers.municipio.dto.ResponseMunicipioDto;
import com.leandroSS.new_thinkers.pessoa.dto.endereco.CreateEnderecoDto;
import com.leandroSS.new_thinkers.pessoa.dto.endereco.ResponseEnderecoDto;
import com.leandroSS.new_thinkers.pessoa.dto.pessoa.CreatePessoaDto;
import com.leandroSS.new_thinkers.pessoa.dto.pessoa.ResponsePessoaDto;
import com.leandroSS.new_thinkers.pessoa.entity.EnderecoEntity;
import com.leandroSS.new_thinkers.pessoa.entity.PessoaEntity;
import com.leandroSS.new_thinkers.pessoa.repository.EnderecoRepository;
import com.leandroSS.new_thinkers.pessoa.repository.PessoaRepository;
import com.leandroSS.new_thinkers.excepition.CustomException;

@Service
public class PessoaService {

        private final PessoaRepository pessoaRepository;
        private final EnderecoRepository enderecoRepository;
        private final BairroRepository bairroRepository;

        public PessoaService(PessoaRepository pessoaRepository,
                        EnderecoRepository enderecoRepository,
                        BairroRepository bairroRepository) {
                this.pessoaRepository = pessoaRepository;
                this.enderecoRepository = enderecoRepository;
                this.bairroRepository = bairroRepository;
        }

        public List<ResponsePessoaDto> createPessoa(CreatePessoaDto createPessoaDto) throws CustomException {

                PessoaValidation.createValidation(createPessoaDto, pessoaRepository, bairroRepository);

                var newPessoa = new PessoaEntity();
                newPessoa.setNome(createPessoaDto.nome());
                newPessoa.setSobrenome(createPessoaDto.sobrenome());
                newPessoa.setIdade(createPessoaDto.idade());
                newPessoa.setLogin(createPessoaDto.login());
                newPessoa.setSenha(createPessoaDto.senha());
                newPessoa.setStatus(createPessoaDto.status());

                this.pessoaRepository.save(newPessoa);

                for (CreateEnderecoDto id : createPessoaDto.endereco()) {

                        var bairro = this.bairroRepository.findByCodigoBairro(id.codigoBairro());

                        var newEndereco = new EnderecoEntity();
                        newEndereco.setBairro(bairro.get(0));
                        newEndereco.setNomeRua(id.nomeRua());
                        newEndereco.setNumero(id.numero());
                        newEndereco.setComplemento(id.complemento());
                        newEndereco.setCep(id.cep());
                        newEndereco.setPessoa(newPessoa);

                        this.enderecoRepository.save(newEndereco);
                }

                var allPessoas = this.pessoaRepository.findAll();

                return allPessoas
                                .stream()
                                .map(pessoa -> new ResponsePessoaDto(
                                                pessoa.getCodigoPessoa(),
                                                pessoa.getNome(),
                                                pessoa.getSobrenome(),
                                                pessoa.getIdade(),
                                                pessoa.getLogin(),
                                                pessoa.getSenha(),
                                                pessoa.getStatus(),
                                                new ArrayList<>()))
                                .toList();

        }

        public List<ResponsePessoaDto> listAllPessoas() {
                var allPessoas = this.pessoaRepository.findAll();

                return allPessoas
                                .stream()
                                .map(pessoa -> new ResponsePessoaDto(
                                                pessoa.getCodigoPessoa(),
                                                pessoa.getNome(),
                                                pessoa.getSobrenome(),
                                                pessoa.getIdade(),
                                                pessoa.getLogin(),
                                                pessoa.getSenha(),
                                                pessoa.getStatus(),
                                                new ArrayList<>()))
                                .toList();
        }

        public List<ResponsePessoaDto> pessoaById(Integer codigoPessoa) {
                var listPessoa = this.pessoaRepository.findByCodigoPessoa(codigoPessoa);

                return listPessoa
                                .stream()
                                .map(pessoa -> new ResponsePessoaDto(
                                                pessoa.getCodigoPessoa(),
                                                pessoa.getNome(),
                                                pessoa.getSobrenome(),
                                                pessoa.getIdade(),
                                                pessoa.getLogin(),
                                                pessoa.getSenha(),
                                                pessoa.getStatus(),
                                                pessoa.getEnderecos()
                                                                .stream()
                                                                .map(endereco -> new ResponseEnderecoDto(
                                                                                endereco.getCodigoEndereco(),
                                                                                endereco.getPessoa().getCodigoPessoa(),
                                                                                endereco.getBairro().getCodigoBairro(),
                                                                                endereco.getNomeRua(),
                                                                                endereco.getNumero(),
                                                                                endereco.getComplemento(),
                                                                                endereco.getCep(),
                                                                                new ResponseBairroDto(
                                                                                                endereco.getBairro()
                                                                                                                .getCodigoBairro(),
                                                                                                endereco.getBairro()
                                                                                                                .getMunicipio()
                                                                                                                .getCodigoMunicipio(),
                                                                                                endereco.getBairro()
                                                                                                                .getNome(),
                                                                                                endereco.getBairro()
                                                                                                                .getStatus(),
                                                                                                new ResponseMunicipioDto(
                                                                                                                endereco.getBairro()
                                                                                                                                .getMunicipio()
                                                                                                                                .getCodigoMunicipio(),
                                                                                                                endereco.getBairro()
                                                                                                                                .getMunicipio()
                                                                                                                                .getUF()
                                                                                                                                .getCodigoUF(),
                                                                                                                endereco.getBairro()
                                                                                                                                .getMunicipio()
                                                                                                                                .getNome(),
                                                                                                                endereco.getBairro()
                                                                                                                                .getMunicipio()
                                                                                                                                .getStatus(),
                                                                                                                new ResponseUfDto(
                                                                                                                                endereco.getBairro()
                                                                                                                                                .getMunicipio()
                                                                                                                                                .getUF()
                                                                                                                                                .getCodigoUF(),
                                                                                                                                endereco.getBairro()
                                                                                                                                                .getMunicipio()
                                                                                                                                                .getUF()
                                                                                                                                                .getSigla(),
                                                                                                                                endereco.getBairro()
                                                                                                                                                .getMunicipio()
                                                                                                                                                .getUF()
                                                                                                                                                .getNome(),
                                                                                                                                endereco.getBairro()
                                                                                                                                                .getMunicipio()
                                                                                                                                                .getUF()
                                                                                                                                                .getStatus())))))
                                                                .toList()))
                                .toList();

        }

        public List<ResponsePessoaDto> pessoaByLogin(String login) {
                var listPessoa = this.pessoaRepository.findByLogin(login);

                return listPessoa
                                .stream()
                                .map(pessoa -> new ResponsePessoaDto(
                                                pessoa.getCodigoPessoa(),
                                                pessoa.getNome(),
                                                pessoa.getSobrenome(),
                                                pessoa.getIdade(),
                                                pessoa.getLogin(),
                                                pessoa.getSenha(),
                                                pessoa.getStatus(),
                                                new ArrayList<>()))
                                .toList();
        }

        public List<ResponsePessoaDto> pessoaByStatus(Integer status) {
                var listPessoa = this.pessoaRepository.findByStatus(status);

                return listPessoa
                                .stream()
                                .map(pessoa -> new ResponsePessoaDto(
                                                pessoa.getCodigoPessoa(),
                                                pessoa.getNome(),
                                                pessoa.getSobrenome(),
                                                pessoa.getIdade(),
                                                pessoa.getLogin(),
                                                pessoa.getSenha(),
                                                pessoa.getStatus(),
                                                new ArrayList<>()))
                                .toList();
        }

        public List<ResponsePessoaDto> uptatePessoa(@RequestBody UpdatePessoaDto updatePessoaDto)
                        throws CustomException {

                var pessoaCurrent = PessoaValidation.updateValidation(updatePessoaDto,
                                pessoaRepository,
                                bairroRepository);

                if (updatePessoaDto.nome() != null) {
                        pessoaCurrent.setNome(updatePessoaDto.nome());
                }

                if (updatePessoaDto.sobrenome() != null) {
                        pessoaCurrent.setSobrenome(updatePessoaDto.sobrenome());
                }

                if (updatePessoaDto.idade() != null) {
                        pessoaCurrent.setIdade(updatePessoaDto.idade());
                }

                if (updatePessoaDto.login() != null) {
                        pessoaCurrent.setLogin(updatePessoaDto.login());
                }

                if (updatePessoaDto.senha() != null) {
                        pessoaCurrent.setSenha(updatePessoaDto.senha());
                }

                if (updatePessoaDto.status() != null) {
                        pessoaCurrent.setStatus(updatePessoaDto.status());
                }

                this.pessoaRepository.save(pessoaCurrent);

                for (EnderecoEntity endereco : pessoaCurrent.getEnderecos()) {
                        var id = endereco.getCodigoEndereco();
                        this.enderecoRepository.deleteById(id);
                }

                for (CreateEnderecoDto endereco : updatePessoaDto.endereco()) {

                        var bairro = this.bairroRepository.findByCodigoBairro(endereco.codigoBairro());

                        var newEndereco = new EnderecoEntity();
                        newEndereco.setNomeRua(endereco.nomeRua());
                        newEndereco.setNumero(endereco.numero());
                        newEndereco.setComplemento(endereco.complemento());
                        newEndereco.setCep(endereco.cep());
                        newEndereco.setBairro(bairro.get(0));
                        newEndereco.setPessoa(pessoaCurrent);

                        this.enderecoRepository.save(newEndereco);
                }

                var allPessoas = this.pessoaRepository.findAll();

                return allPessoas
                                .stream()
                                .map(pessoa -> new ResponsePessoaDto(
                                                pessoa.getCodigoPessoa(),
                                                pessoa.getNome(),
                                                pessoa.getSobrenome(),
                                                pessoa.getIdade(),
                                                pessoa.getLogin(),
                                                pessoa.getSenha(),
                                                pessoa.getStatus(),
                                                new ArrayList<>()))
                                .toList();

        }

}