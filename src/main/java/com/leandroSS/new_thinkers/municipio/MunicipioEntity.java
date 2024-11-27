package com.leandroSS.new_thinkers.municipio;

import com.leandroSS.new_thinkers.UF.UfEntity;
import com.leandroSS.new_thinkers.bairro.BairroEntity;
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
    @JoinColumn(name = "codigoUf")
    private UfEntity uf;

    @OneToMany(mappedBy = "municipio")
    private List<BairroEntity> bairros = new ArrayList<>();

    public MunicipioEntity() {
    }

    public MunicipioEntity(Integer codigoMunicipio, String nome, Integer status, UfEntity uf) {
        this.codigoMunicipio = codigoMunicipio;
        this.nome = nome;
        this.status = status;
        this.uf = uf;
    }
}
