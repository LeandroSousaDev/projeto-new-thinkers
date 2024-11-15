package com.leandroSS.new_thinkers.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "TB_UF")
@Data
public class UF {
    @Id
    @Column(name = "CODIGO_UF")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer codigoUf;

    @Column(name = "SIGLA")
    private String sigla;

    @Column(name = "NOME")
    private String nome;

    @Column(name = "STATUS")
    private Integer status;

    @OneToMany(mappedBy = "uf")
    private List<Municipio> municipios = new ArrayList<>();

    public UF() {
    }

    public UF(Integer codigoUf, String sigla, String nome, Integer status) {
        this.codigoUf = codigoUf;
        this.sigla = sigla;
        this.nome = nome;
        this.status = status;
    }
}
