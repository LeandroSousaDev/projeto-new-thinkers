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

       List<Endereco> endereco = new ArrayList<>();

        for(CreateEnderecoDto id : createPessoaDto.endereco()) {
            var codigoBairro = id.codigoBairro();

            var bairro = this.bairroRepository.findById(codigoBairro)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatusCode.valueOf(404),
                            "bairro não exixste"));

           var  newEndereco = new Endereco();
            newEndereco.setBairro(bairro);
            newEndereco.setNomeRua(id.nomeRua());
            newEndereco.setNumero(id.numero());
            newEndereco.setComplemento(id.complemento());
            newEndereco.setCep(id.cep());

            endereco.add(newEndereco);
        }

        var newPessoa = new Pessoa();
        newPessoa.setNome(createPessoaDto.nome());
        newPessoa.setSobrenome(createPessoaDto.sobrenome());
        newPessoa.setIdade(createPessoaDto.idade());
        newPessoa.setLogin(createPessoaDto.login());
        newPessoa.setSenha(createPessoaDto.senha());
        newPessoa.setStatus(createPessoaDto.status());
        newPessoa.setEnderecos(endereco);

        this.pessoaRepository.save(newPessoa);



        List<ResponseEnderecoDto> responseEndereco = new ArrayList<>();

        for(Endereco pessoa: endereco) {
            pessoa.setPessoa(newPessoa);
            this.enderecoRepository.save(pessoa);

            var newResponseEndereco = new ResponseEnderecoDto(
                    pessoa.getCodigoEndereco(),
                    pessoa.getPessoa().getCodigoPessoa(),
                    pessoa.getBairro().getCodigoBairro(),
                    pessoa.getNomeRua(),
                    pessoa.getNumero(),
                    pessoa.getComplemento(),
                    pessoa.getCep(),
                    null
            );

            responseEndereco.add(newResponseEndereco);
        }


        return new ResponsePessoaDto(
                newPessoa.getCodigoPessoa(),
                newPessoa.getNome(),
                newPessoa.getSobrenome(),
                newPessoa.getIdade(),
                newPessoa.getLogin(),
                newPessoa.getSenha(),
                newPessoa.getStatus(),
                responseEndereco
        );
    }

    public List<ResponsePessoaDto> listAllPessoas() {
        var allPessoas = this.pessoaRepository.findAll();

        return allPessoas
                .stream()
                .map(pessoa -> new  ResponsePessoaDto(
                        pessoa.getCodigoPessoa(),
                        pessoa.getNome(),
                        pessoa.getSobrenome(),
                        pessoa.getIdade(),
                        pessoa.getLogin(),
                        pessoa.getSenha(),
                        pessoa.getStatus(),
                        pessoa.getEnderecos().stream()
                                .map(endereco -> new ResponseEnderecoDto(
                                        endereco.getCodigoEndereco(),
                                        endereco.getPessoa().getCodigoPessoa(),
                                        endereco.getBairro().getCodigoBairro(),
                                        endereco.getNomeRua(),
                                        endereco.getNumero(),
                                        endereco.getComplemento(),
                                        endereco.getCep(),
                                        null)).toList()
                        )).toList();
    }
}
