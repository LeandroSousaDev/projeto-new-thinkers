package com.leandroSS.new_thinkers.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "TB_MUNICIPIO")
@Data
public class Municipio {
    @Id
    @Column(name = "CODIGO_MUNICIPIO")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer codigoMunicipio;

    @Column(name = "NOME")
    private String nome;

    @Column(name = "STATUS")
    private Integer status;

    @ManyToOne
    @JoinColumn(name = "codigoUf")
    private UF uf;

    @OneToMany(mappedBy = "municipio")
    private List<Bairro> bairros = new ArrayList<>();

    public Municipio() {
    }

    public Municipio(Integer codigoMunicipio, String nome, Integer status, UF uf) {
        this.codigoMunicipio = codigoMunicipio;
        this.nome = nome;
        this.status = status;
        this.uf = uf;
    }
}
