package com.leandroSS.new_thinkers.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "TB_ENDERECO")
@Data
public class Endereco {

    @Id
    @Column(name = "CODIGO_ENDERECO")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer codigoEndereco;

    @Column(name = "NOME_RUA")
    private String nomeRua;

    @Column(name = "NUMERO")
    private String numero;

    @Column(name = "COMPLEMENTO")
    private String complemento;

    @Column(name = "CEP")
    private String cep;

    @ManyToOne
    @JoinColumn(name = "codigoPessoa")
    private Pessoa pessoa;

    @ManyToOne
    @JoinColumn(name = "codigoBairro")
    private Bairro bairro;

    public Endereco() {
    }

    public Endereco(Integer codigoEndereco,
                    String nomeRua,
                    String numero,
                    String complemento,
                    String cep,
                    Pessoa pessoa,
                    Bairro bairro) {
        this.codigoEndereco = codigoEndereco;
        this.nomeRua = nomeRua;
        this.numero = numero;
        this.complemento = complemento;
        this.cep = cep;
        this.pessoa = pessoa;
        this.bairro = bairro;
    }
}
