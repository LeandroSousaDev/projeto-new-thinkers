package com.leandroSS.new_thinkers.service;

import com.leandroSS.new_thinkers.dto.Bairro.CreateBairroDto;
import com.leandroSS.new_thinkers.dto.Bairro.ResponseBairroDto;
import com.leandroSS.new_thinkers.entity.Bairro;
import com.leandroSS.new_thinkers.repository.BairroRepository;
import com.leandroSS.new_thinkers.repository.MunicipioRepository;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class BairroService {

        private final BairroRepository bairroRepository;
        private final MunicipioRepository municipioRepository;

        public BairroService(BairroRepository bairroRepository, MunicipioRepository municipioRepository) {
                this.bairroRepository = bairroRepository;
                this.municipioRepository = municipioRepository;
        }

        public List<ResponseBairroDto> createBairro(CreateBairroDto createBairroDto) {

                var municipio = this.municipioRepository.findById(createBairroDto.codigoMunicipio())
                                .orElseThrow(() -> new ResponseStatusException(HttpStatusCode.valueOf(404),
                                                "Municipio não exixste"));

                var newBairro = new Bairro();
                newBairro.setStatus(createBairroDto.status());
                newBairro.setNome(createBairroDto.nome());
                newBairro.setMunicipio(municipio);

                this.bairroRepository.save(newBairro);

                var allBairro = this.bairroRepository.findAll();

                return allBairro
                                .stream()
                                .map(bairros -> new ResponseBairroDto(
                                                bairros.getCodigoBairro(),
                                                bairros.getMunicipio().getCodigoMunicipio(),
                                                bairros.getNome(),
                                                bairros.getStatus(),
                                                null))
                                .toList();
        }

        public List<ResponseBairroDto> listAllBairro() {
                var allBairro = this.bairroRepository.findAll();

                return allBairro
                                .stream()
                                .map(bairros -> new ResponseBairroDto(
                                                bairros.getCodigoBairro(),
                                                bairros.getMunicipio().getCodigoMunicipio(),
                                                bairros.getNome(),
                                                bairros.getStatus(),
                                                null))
                                .toList();
        }

        public List<ResponseBairroDto> bairroByNome(String nome) {
                var listBairro = this.bairroRepository.findByNome(nome);

                return listBairro
                                .stream()
                                .map(bairro -> new ResponseBairroDto(
                                                bairro.getCodigoBairro(),
                                                bairro.getMunicipio().getCodigoMunicipio(),
                                                bairro.getNome(),
                                                bairro.getStatus(),
                                                null))
                                .toList();
        }

        public List<ResponseBairroDto> bairroByStatus(Integer status) {
                var listBairro = this.bairroRepository.findByStatus(status);

                return listBairro
                                .stream()
                                .map(bairro -> new ResponseBairroDto(
                                                bairro.getCodigoBairro(),
                                                bairro.getMunicipio().getCodigoMunicipio(),
                                                bairro.getNome(),
                                                bairro.getStatus(),
                                                null))
                                .toList();
        }

        public ResponseBairroDto bairroById(Integer codigoBairro) {
                var listMunicipio = this.bairroRepository.findByCodigoBairro(codigoBairro);

                return new ResponseBairroDto(
                                listMunicipio.getCodigoBairro(),
                                listMunicipio.getMunicipio().getCodigoMunicipio(),
                                listMunicipio.getNome(),
                                listMunicipio.getStatus(),
                                null);
        }

        public List<ResponseBairroDto> bairroByMunicipio(String municipio) {
                var id = Integer.valueOf(municipio);
                var municipioCurrent = this.municipioRepository.findById(id)
                                .orElseThrow(() -> new ResponseStatusException(HttpStatusCode.valueOf(404),
                                                "municipio não exixste"));

                var listBairro = this.bairroRepository.findByMunicipio(municipioCurrent);

                return listBairro
                                .stream()
                                .map(bairro -> new ResponseBairroDto(
                                                bairro.getCodigoBairro(),
                                                bairro.getMunicipio().getCodigoMunicipio(),
                                                bairro.getNome(),
                                                bairro.getStatus(),
                                                null))
                                .toList();

        }

        public List<ResponseBairroDto> updateBairro(String codigoBairro, Bairro bairro) {

                var id = Integer.valueOf(codigoBairro);
                var bairroCurrent = this.bairroRepository.findById(id)
                                .orElseThrow(() -> new ResponseStatusException(HttpStatusCode.valueOf(404),
                                                "Bairro não exixste"));

                if (bairro.getNome() != null) {
                        bairroCurrent.setNome(bairro.getNome());
                }

                if (bairro.getStatus() != null) {
                        bairroCurrent.setStatus(bairro.getStatus());
                }

                this.bairroRepository.save(bairroCurrent);

                var allBairro = this.bairroRepository.findAll();

                return allBairro
                                .stream()
                                .map(bairros -> new ResponseBairroDto(
                                                bairros.getCodigoBairro(),
                                                bairros.getMunicipio().getCodigoMunicipio(),
                                                bairros.getNome(),
                                                bairros.getStatus(),
                                                null))
                                .toList();

        }

}
