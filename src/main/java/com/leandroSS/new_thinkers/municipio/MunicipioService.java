package com.leandroSS.new_thinkers.municipio;

import com.leandroSS.new_thinkers.municipio.dto.CreateMunicipioDto;
import com.leandroSS.new_thinkers.municipio.dto.ResponseMunicipioDto;
import com.leandroSS.new_thinkers.UF.UfRepository;
import com.leandroSS.new_thinkers.utils.excepition.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MunicipioService {

        private final MunicipioRepository municipioRepository;
        private final UfRepository ufRepository;

        public MunicipioService(MunicipioRepository municipioRepository, UfRepository ufRepository) {
                this.municipioRepository = municipioRepository;
                this.ufRepository = ufRepository;
        }

        public List<ResponseMunicipioDto> createMunicipio(CreateMunicipioDto createMunicipioDto)
                        throws NotFoundException {

                var uf = this.ufRepository.findByCodigoUf(createMunicipioDto.codigoUf());

                if (uf == null) {
                        throw new NotFoundException("Estado não encontrado");
                }

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

        public List<ResponseMunicipioDto> municipuioByUF(String uf) throws NotFoundException {
                var id = Integer.valueOf(uf);
                var ufCurrent = this.ufRepository.findByCodigoUf(id);

                if (ufCurrent == null) {
                        throw new NotFoundException("Estado não encontrado");
                }

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
                        CreateMunicipioDto createMunicipioDto) throws NotFoundException {

                var id = Integer.valueOf(codigoMunicipio);
                var municipioCurrent = this.municipioRepository.findByCodigoMunicipio(id);

                if (municipioCurrent == null) {
                        throw new NotFoundException("Municipio não encontrado");
                }

                var uf = this.ufRepository.findByCodigoUf(createMunicipioDto.codigoUf());

                if (uf == null) {
                        throw new NotFoundException("Estado não encontrado");
                }

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