package com.leandroSS.new_thinkers.pessoa.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "TB_PESSOA")
@Data
public class PessoaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "CODIGO_PESSOA")
    private Integer codigoPessoa;

    @Column(name = "NOME")
    private String nome;

    @Column(name = "SOBRENOME")
    private String sobrenome;

    @Column(name = "IDADE")
    private Integer idade;

    @Column(name = "LOGIN")
    private String login;

    @Column(name = "SENHA")
    private String senha;

    @Column(name = "STATUS")
    private Integer status;

    @OneToMany(mappedBy = "pessoa")
    private List<EnderecoEntity> enderecos = new ArrayList<>();

    public PessoaEntity() {
    }

    public PessoaEntity(Integer codigoPessoa,
                        String nome,
                        String sobrenome,
                        Integer idade,
                        String login,
                        String senha,
                        Integer status,
                        List<EnderecoEntity> enderecos) {
        this.codigoPessoa = codigoPessoa;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.idade = idade;
        this.login = login;
        this.senha = senha;
        this.status = status;
        this.enderecos = enderecos;
    }
}
