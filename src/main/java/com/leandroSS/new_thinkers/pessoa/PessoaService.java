package com.leandroSS.new_thinkers.pessoa;

import com.leandroSS.new_thinkers.bairro.dto.ResponseBairroDto;
import com.leandroSS.new_thinkers.pessoa.dto.endereco.CreateEnderecoDto;
import com.leandroSS.new_thinkers.pessoa.entity.Endereco;
import com.leandroSS.new_thinkers.pessoa.entity.Pessoa;
import com.leandroSS.new_thinkers.pessoa.dto.pessoa.CreatePessoaDto;
import com.leandroSS.new_thinkers.pessoa.dto.endereco.ResponseEnderecoDto;
import com.leandroSS.new_thinkers.municipio.dto.ResponseMunicipioDto;
import com.leandroSS.new_thinkers.pessoa.dto.pessoa.ResponsePessoaDto;
import com.leandroSS.new_thinkers.UF.dto.ResponseUfDto;
import com.leandroSS.new_thinkers.bairro.BairroRepository;
import com.leandroSS.new_thinkers.pessoa.repository.EnderecoRepository;
import com.leandroSS.new_thinkers.pessoa.repository.PessoaRepository;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

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

        public ResponsePessoaDto createPessoa(CreatePessoaDto createPessoaDto) {

                List<Endereco> enderecos = new ArrayList<>();

                for (CreateEnderecoDto id : createPessoaDto.endereco()) {
                        var codigoBairro = id.codigoBairro();

                        var bairro = this.bairroRepository.findById(codigoBairro)
                                        .orElseThrow(() -> new ResponseStatusException(HttpStatusCode.valueOf(404),
                                                        "bairro não exixste"));

                        var newEndereco = new Endereco();
                        newEndereco.setBairro(bairro);
                        newEndereco.setNomeRua(id.nomeRua());
                        newEndereco.setNumero(id.numero());
                        newEndereco.setComplemento(id.complemento());
                        newEndereco.setCep(id.cep());

                        enderecos.add(newEndereco);
                }

                var newPessoa = new Pessoa();
                newPessoa.setNome(createPessoaDto.nome());
                newPessoa.setSobrenome(createPessoaDto.sobrenome());
                newPessoa.setIdade(createPessoaDto.idade());
                newPessoa.setLogin(createPessoaDto.login());
                newPessoa.setSenha(createPessoaDto.senha());
                newPessoa.setStatus(createPessoaDto.status());

                this.pessoaRepository.save(newPessoa);

                for (Endereco pessoa : enderecos) {
                        pessoa.setPessoa(newPessoa);
                        this.enderecoRepository.save(pessoa);
                }

                return new ResponsePessoaDto(
                                newPessoa.getCodigoPessoa(),
                                newPessoa.getNome(),
                                newPessoa.getSobrenome(),
                                newPessoa.getIdade(),
                                newPessoa.getLogin(),
                                newPessoa.getSenha(),
                                newPessoa.getStatus(),
                                newPessoa.getEnderecos()
                                                .stream()
                                                .map(endereco -> new ResponseEnderecoDto(
                                                                endereco.getCodigoEndereco(),
                                                                endereco.getPessoa().getCodigoPessoa(),
                                                                endereco.getBairro().getCodigoBairro(),
                                                                endereco.getNomeRua(),
                                                                endereco.getNumero(),
                                                                endereco.getComplemento(),
                                                                endereco.getCep(),
                                                                null))
                                                .toList());
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
                                                                                null))
                                                                .toList()))
                                .toList();
        }

        public ResponsePessoaDto pessoaById(Integer codigoPessoa) {
                var listPessoa = this.pessoaRepository.findByCodigoPessoa(codigoPessoa);

                var pessoaCurrent = new ResponsePessoaDto(
                                listPessoa.getCodigoPessoa(),
                                listPessoa.getNome(),
                                listPessoa.getSobrenome(),
                                listPessoa.getIdade(),
                                listPessoa.getLogin(),
                                listPessoa.getSenha(),
                                listPessoa.getStatus(),
                                listPessoa.getEnderecos()
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
                                                                                endereco.getBairro().getCodigoBairro(),
                                                                                endereco.getBairro().getMunicipio()
                                                                                                .getCodigoMunicipio(),
                                                                                endereco.getBairro().getNome(),
                                                                                endereco.getBairro().getStatus(),
                                                                                new ResponseMunicipioDto(
                                                                                                endereco.getBairro()
                                                                                                                .getMunicipio()
                                                                                                                .getCodigoMunicipio(),
                                                                                                endereco.getBairro()
                                                                                                                .getMunicipio()
                                                                                                                .getUf()
                                                                                                                .getCodigoUf(),
                                                                                                endereco.getBairro()
                                                                                                                .getMunicipio()
                                                                                                                .getNome(),
                                                                                                endereco.getBairro()
                                                                                                                .getMunicipio()
                                                                                                                .getStatus(),
                                                                                                new ResponseUfDto(
                                                                                                                endereco.getBairro()
                                                                                                                                .getMunicipio()
                                                                                                                                .getUf()
                                                                                                                                .getCodigoUf(),
                                                                                                                endereco.getBairro()
                                                                                                                                .getMunicipio()
                                                                                                                                .getUf()
                                                                                                                                .getSigla(),
                                                                                                                endereco.getBairro()
                                                                                                                                .getMunicipio()
                                                                                                                                .getUf()
                                                                                                                                .getNome(),
                                                                                                                endereco.getBairro()
                                                                                                                                .getMunicipio()
                                                                                                                                .getUf()
                                                                                                                                .getStatus())))))
                                                .toList());

                return pessoaCurrent;
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

        public ResponsePessoaDto uptatePessoa(@PathVariable String codigoPessoa,
                        @RequestBody CreatePessoaDto createPessoaDto) {

                var pessoa = this.pessoaRepository.findById(Integer.valueOf(codigoPessoa))
                                .orElseThrow(() -> new ResponseStatusException(HttpStatusCode.valueOf(404),
                                                "pessoa não exixste"));

                if (createPessoaDto.nome() != null) {
                        pessoa.setNome(createPessoaDto.nome());
                }

                if (createPessoaDto.sobrenome() != null) {
                        pessoa.setSobrenome(createPessoaDto.sobrenome());
                }

                if (createPessoaDto.idade() != null) {
                        pessoa.setIdade(createPessoaDto.idade());
                }

                if (createPessoaDto.login() != null) {
                        pessoa.setLogin(createPessoaDto.login());
                }

                if (createPessoaDto.senha() != null) {
                        pessoa.setSenha(createPessoaDto.senha());
                }

                if (createPessoaDto.status() != null) {
                        pessoa.setStatus(createPessoaDto.status());
                }

                this.pessoaRepository.save(pessoa);

                for (Endereco endereco : pessoa.getEnderecos()) {
                        var id = endereco.getCodigoEndereco();
                        this.enderecoRepository.deleteById(id);
                }

                for (CreateEnderecoDto endereco : createPessoaDto.endereco()) {

                        var bairro = this.bairroRepository.findById(endereco.codigoBairro())
                                        .orElseThrow(() -> new ResponseStatusException(HttpStatusCode.valueOf(404),
                                                        "bairro não exixste"));

                        var newEndereco = new Endereco();
                        newEndereco.setNomeRua(endereco.nomeRua());
                        newEndereco.setNumero(endereco.numero());
                        newEndereco.setComplemento(endereco.complemento());
                        newEndereco.setCep(endereco.cep());
                        newEndereco.setBairro(bairro);
                        newEndereco.setPessoa(pessoa);

                        this.enderecoRepository.save(newEndereco);
                }

                return new ResponsePessoaDto(
                                pessoa.getCodigoPessoa(),
                                pessoa.getNome(),
                                pessoa.getSobrenome(),
                                pessoa.getIdade(),
                                pessoa.getLogin(),
                                pessoa.getSenha(),
                                pessoa.getStatus(),
                                new ArrayList<>());
        }

}