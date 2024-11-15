package com.leandroSS.new_thinkers.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "TB_BAIRRO")
@Data
public class Bairro {
    @Id
    @Column(name = "CODIGO_BAIRRO")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer codigoBairro;

    @Column(name = "NOME")
    private String nome;

    @Column(name = "STATUS")
    private Integer status;

    @ManyToOne
    @JoinColumn(name = "codigoMunicipio")
    private Municipio municipio;

    @OneToMany
    private List<Endereco> enderecos = new ArrayList<>();

    public Bairro() {
    }

    public Bairro(Integer codigoBairro, String nome, Integer status, Municipio municipio) {
        this.codigoBairro = codigoBairro;
        this.nome = nome;
        this.status = status;
        this.municipio = municipio;
    }
}
