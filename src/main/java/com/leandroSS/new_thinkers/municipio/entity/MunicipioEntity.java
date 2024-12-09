package com.leandroSS.new_thinkers.municipio.entity;

import com.leandroSS.new_thinkers.UF.Entity.UfEntity;
import com.leandroSS.new_thinkers.bairro.entity.BairroEntity;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "TB_MUNICIPIO")
@Data
public class MunicipioEntity {
    @Id
    @Column(name = "CODIGO_MUNICIPIO")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer codigoMunicipio;

    @Column(name = "NOME")
    private String nome;

    @Column(name = "STATUS")
    private Integer status;

    @ManyToOne
    @JoinColumn(name = "codigoUF")
    private UfEntity UF;

    @OneToMany(mappedBy = "municipio")
    private List<BairroEntity> bairros = new ArrayList<>();

    public MunicipioEntity() {
    }

    public MunicipioEntity(Integer codigoMunicipio, String nome, Integer status, UfEntity UF) {
        this.codigoMunicipio = codigoMunicipio;
        this.nome = nome;
        this.status = status;
        this.UF = UF;
    }
}
