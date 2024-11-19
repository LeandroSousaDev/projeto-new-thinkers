package com.leandroSS.new_thinkers.service;

import com.leandroSS.new_thinkers.dto.Endereco.CreateEnderecoDto;
import com.leandroSS.new_thinkers.dto.Pessoa.CreatePessoaDto;
import com.leandroSS.new_thinkers.dto.Endereco.ResponseEnderecoDto;
import com.leandroSS.new_thinkers.dto.Pessoa.ResponsePessoaDto;
import com.leandroSS.new_thinkers.entity.Endereco;
import com.leandroSS.new_thinkers.entity.Pessoa;
import com.leandroSS.new_thinkers.repository.BairroRepository;
import com.leandroSS.new_thinkers.repository.EnderecoRepository;
import com.leandroSS.new_thinkers.repository.PessoaRepository;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
        newPessoa.setEnderecos(enderecos);

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

    //////////////////////////////////////////////////////////////////////////////////

    public ResponseEnderecoDto updateEndereco(@PathVariable String codigoEndereco,
            @RequestBody CreateEnderecoDto createEnderecoDto) {

        var endereco = this.enderecoRepository.findById(Integer.valueOf(codigoEndereco))
                .orElseThrow(() -> new ResponseStatusException(HttpStatusCode.valueOf(404),
                        "Endereco não exixste"));

        if (createEnderecoDto.nomeRua() != null) {
            endereco.setNomeRua(createEnderecoDto.nomeRua());
        }

        if (createEnderecoDto.numero() != null) {
            endereco.setNumero(createEnderecoDto.numero());
        }

        if (createEnderecoDto.complemento() != null) {
            endereco.setComplemento(createEnderecoDto.complemento());
        }

        if (createEnderecoDto.cep() != null) {
            endereco.setCep(createEnderecoDto.cep());
        }

        this.enderecoRepository.save(endereco);

        return new ResponseEnderecoDto(
                endereco.getCodigoEndereco(),
                endereco.getPessoa().getCodigoPessoa(),
                endereco.getBairro().getCodigoBairro(),
                endereco.getNomeRua(),
                endereco.getNumero(),
                endereco.getComplemento(),
                endereco.getCep(),
                null);
    }

    public void deleteEndereco(@PathVariable String codigoEndereco) {

        var id = Integer.valueOf(codigoEndereco);
        this.enderecoRepository.findById(Integer.valueOf(codigoEndereco))
                .orElseThrow(() -> new ResponseStatusException(HttpStatusCode.valueOf(404),
                        "Endereco não exixste"));

        this.enderecoRepository.deleteById(id);
    }

    public ResponsePessoaDto addEndereco(@PathVariable String codigoPessoa,
            @RequestBody CreateEnderecoDto createEnderecoDto) {
        var pessoa = this.pessoaRepository.findById(Integer.valueOf(codigoPessoa))
                .orElseThrow(() -> new ResponseStatusException(HttpStatusCode.valueOf(404),
                        "pessoa não exixste"));

        var bairro = this.bairroRepository.findById(createEnderecoDto.codigoBairro())
                .orElseThrow(() -> new ResponseStatusException(HttpStatusCode.valueOf(404),
                        "bairo não exixste"));

        var newEndereco = new Endereco();
        newEndereco.setNomeRua(createEnderecoDto.nomeRua());
        newEndereco.setNumero(createEnderecoDto.numero());
        newEndereco.setComplemento(createEnderecoDto.complemento());
        newEndereco.setCep(createEnderecoDto.cep());
        newEndereco.setBairro(bairro);
        newEndereco.setPessoa(pessoa);

        this.enderecoRepository.save(newEndereco);

        return new ResponsePessoaDto(
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
                        .toList());
    }
}
