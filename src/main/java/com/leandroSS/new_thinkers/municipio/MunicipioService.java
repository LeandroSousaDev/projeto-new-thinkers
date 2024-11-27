package com.leandroSS.new_thinkers.municipio;

import com.leandroSS.new_thinkers.municipio.dto.CreateMunicipioDto;
import com.leandroSS.new_thinkers.municipio.dto.ResponseMunicipioDto;
import com.leandroSS.new_thinkers.UF.UfRepository;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class MunicipioService {

        private final MunicipioRepository municipioRepository;
        private final UfRepository ufRepository;

        public MunicipioService(MunicipioRepository municipioRepository, UfRepository ufRepository) {
                this.municipioRepository = municipioRepository;
                this.ufRepository = ufRepository;
        }

        public List<ResponseMunicipioDto> createMunicipio(CreateMunicipioDto createMunicipioDto) {

                var uf = this.ufRepository.findById(createMunicipioDto.codigoUf())
                                .orElseThrow(() -> new ResponseStatusException(HttpStatusCode.valueOf(404),
                                                "Estado não exixste"));

                var newMunicipio = new MunicipioEntity();
                newMunicipio.setStatus(createMunicipioDto.status());
                newMunicipio.setNome(createMunicipioDto.nome());
                newMunicipio.setUf(uf);

                this.municipioRepository.save(newMunicipio);

                var allMunicipios = this.municipioRepository.findAll();

                return allMunicipios
                                .stream()
                                .map(municipios -> new ResponseMunicipioDto(
                                                municipios.getCodigoMunicipio(),
                                                municipios.getUf().getCodigoUf(),
                                                municipios.getNome(),
                                                municipios.getStatus(),
                                                null))
                                .toList();

        }

        public List<ResponseMunicipioDto> listAllMunicipio() {
                var allMunicipios = this.municipioRepository.findAll();

                return allMunicipios
                                .stream()
                                .map(municipio -> new ResponseMunicipioDto(
                                                municipio.getCodigoMunicipio(),
                                                municipio.getUf().getCodigoUf(),
                                                municipio.getNome(),
                                                municipio.getStatus(),
                                                null))
                                .toList();
        }

        public List<ResponseMunicipioDto> municipioByNome(String nome) {
                var listMunicipio = this.municipioRepository.findByNome(nome);

                return listMunicipio
                                .stream()
                                .map(municipio -> new ResponseMunicipioDto(
                                                municipio.getCodigoMunicipio(),
                                                municipio.getUf().getCodigoUf(),
                                                municipio.getNome(),
                                                municipio.getStatus(),
                                                null))
                                .toList();
        }

        public List<ResponseMunicipioDto> municipioByStatus(Integer status) {
                var listMunicipio = this.municipioRepository.findByStatus(status);

                return listMunicipio
                                .stream()
                                .map(municipio -> new ResponseMunicipioDto(
                                                municipio.getCodigoMunicipio(),
                                                municipio.getUf().getCodigoUf(),
                                                municipio.getNome(),
                                                municipio.getStatus(),
                                                null))
                                .toList();
        }

        public ResponseMunicipioDto municipioById(Integer codigoMunicipio) {
                var listMunicipio = this.municipioRepository.findByCodigoMunicipio(codigoMunicipio);

                return new ResponseMunicipioDto(
                                listMunicipio.getCodigoMunicipio(),
                                listMunicipio.getUf().getCodigoUf(),
                                listMunicipio.getNome(),
                                listMunicipio.getStatus(),
                                null);
        }

        public List<ResponseMunicipioDto> municipuioByUF(String uf) {
                var id = Integer.valueOf(uf);
                var ufCurrent = this.ufRepository.findById(id)
                                .orElseThrow(() -> new ResponseStatusException(HttpStatusCode.valueOf(404),
                                                "Estado não exixste"));

                var listMunicipio = this.municipioRepository.findByUf(ufCurrent);

                return listMunicipio
                                .stream()
                                .map(municipio -> new ResponseMunicipioDto(
                                                municipio.getCodigoMunicipio(),
                                                municipio.getUf().getCodigoUf(),
                                                municipio.getNome(),
                                                municipio.getStatus(),
                                                null))
                                .toList();

        }

        public List<ResponseMunicipioDto> updateMunicipio(String codigoMunicipio,
                        CreateMunicipioDto createMunicipioDto) {

                var id = Integer.valueOf(codigoMunicipio);
                var municipioCurrent = this.municipioRepository.findById(id)
                                .orElseThrow(() -> new ResponseStatusException(HttpStatusCode.valueOf(404),
                                                "municipio não exixste"));

                var uf = this.ufRepository.findById(createMunicipioDto.codigoUf())
                                .orElseThrow(() -> new ResponseStatusException(HttpStatusCode.valueOf(404),
                                                "Estado não exixste"));

                if (createMunicipioDto.nome() != null) {
                        municipioCurrent.setNome(createMunicipioDto.nome());
                }

                if (createMunicipioDto.status() != null) {
                        municipioCurrent.setStatus(createMunicipioDto.status());
                }

                if (createMunicipioDto.codigoUf() != null) {
                        municipioCurrent.setUf(uf);
                }

                this.municipioRepository.save(municipioCurrent);

                var allMunicipios = this.municipioRepository.findAll();

                return allMunicipios
                                .stream()
                                .map(municipios -> new ResponseMunicipioDto(
                                                municipios.getCodigoMunicipio(),
                                                municipios.getUf().getCodigoUf(),
                                                municipios.getNome(),
                                                municipios.getStatus(),
                                                null))
                                .toList();

        }
}