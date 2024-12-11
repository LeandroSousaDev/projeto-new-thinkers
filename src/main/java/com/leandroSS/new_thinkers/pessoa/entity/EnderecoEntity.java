package com.leandroSS.new_thinkers.pessoa.entity;

import com.leandroSS.new_thinkers.bairro.entity.BairroEntity;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "TB_ENDERECO")
@Data
public class EnderecoEntity {

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
    private PessoaEntity pessoa;

    @ManyToOne
    @JoinColumn(name = "codigoBairro")
    private BairroEntity bairro;

    public EnderecoEntity() {
    }

    public EnderecoEntity(Integer codigoEndereco,
                          String nomeRua,
                          String numero,
                          String complemento,
                          String cep,
                          PessoaEntity pessoa,
                          BairroEntity bairro) {
        this.codigoEndereco = codigoEndereco;
        this.nomeRua = nomeRua;
        this.numero = numero;
        this.complemento = complemento;
        this.cep = cep;
        this.pessoa = pessoa;
        this.bairro = bairro;
    }
}
