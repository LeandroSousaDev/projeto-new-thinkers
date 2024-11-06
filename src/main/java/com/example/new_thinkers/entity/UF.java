package com.example.new_thinkers.entity;

import jakarta.persistence.*;
import lombok.Data;

//adicionar not null nas variaveis
@Entity
@Table(name = "TB_UF")
@Data
public class UF {
    @Id
    @Column(name = "CODIGO_UF")
    @GeneratedValue(generator = "Interger")
    private Integer codigo_uf;

    @Column(name = "SIGLA")
    private String sigla;

    @Column(name = "NOME")
    private String nome;

    @Column(name = "STATUS")
    private Integer status;

    public UF() {
    }

    public UF(Integer codigo_uf, String sigla, String nome, Integer status) {
        this.codigo_uf = codigo_uf;
        this.sigla = sigla;
        this.nome = nome;
        this.status = status;
    }
}
