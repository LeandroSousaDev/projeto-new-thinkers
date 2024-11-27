package com.leandroSS.new_thinkers.UF;

import com.leandroSS.new_thinkers.municipio.MunicipioEntity;
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
    private Integer codigoUf;

    @Column(name = "SIGLA")
    private String sigla;

    @Column(name = "NOME")
    private String nome;

    @Column(name = "STATUS")
    private Integer status;

    @OneToMany(mappedBy = "uf")
    private List<MunicipioEntity> municipios = new ArrayList<>();

    public UfEntity() {
    }

    public UfEntity(Integer codigoUf, String sigla, String nome, Integer status) {
        this.codigoUf = codigoUf;
        this.sigla = sigla;
        this.nome = nome;
        this.status = status;
    }
}
