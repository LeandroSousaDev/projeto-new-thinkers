package com.leandroSS.new_thinkers.UF.Entity;

import com.leandroSS.new_thinkers.municipio.entity.MunicipioEntity;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "TB_UF")
@Data
public class UfEntity {
    @Id
    @Column(name = "CODIGO_UF")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer codigoUF;

    @Column(name = "SIGLA")
    private String sigla;

    @Column(name = "NOME")
    private String nome;

    @Column(name = "STATUS")
    private Integer status;

    @OneToMany(mappedBy = "UF")
    private List<MunicipioEntity> municipios = new ArrayList<>();

    public UfEntity() {
    }

    public UfEntity(Integer codigoUF, String sigla, String nome, Integer status) {
        this.codigoUF = codigoUF;
        this.sigla = sigla;
        this.nome = nome;
        this.status = status;
    }
}
