package com.leandroSS.new_thinkers.bairro;

import com.leandroSS.new_thinkers.pessoa.entity.EnderecoEntity;
import com.leandroSS.new_thinkers.municipio.MunicipioEntity;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "TB_BAIRRO")
@Data
public class BairroEntity {
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
    private MunicipioEntity municipio;

    @OneToMany(mappedBy = "bairro")
    private List<EnderecoEntity> enderecos = new ArrayList<>();

    public BairroEntity() {
    }

    public BairroEntity(Integer codigoBairro, String nome, Integer status, MunicipioEntity municipio) {
        this.codigoBairro = codigoBairro;
        this.nome = nome;
        this.status = status;
        this.municipio = municipio;
    }
}
