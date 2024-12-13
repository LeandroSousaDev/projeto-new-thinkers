package com.leandroSS.new_thinkers.municipio.service;

import com.leandroSS.new_thinkers.municipio.dto.CreateMunicipioDto;
import com.leandroSS.new_thinkers.municipio.dto.ResponseMunicipioDto;
import com.leandroSS.new_thinkers.UF.repository.UfRepository;
import com.leandroSS.new_thinkers.municipio.dto.UpdateMunicipioDto;
import com.leandroSS.new_thinkers.municipio.entity.MunicipioEntity;
import com.leandroSS.new_thinkers.municipio.repository.MunicipioRepository;
import com.leandroSS.new_thinkers.excepition.CustomException;
import com.leandroSS.new_thinkers.municipio.validation.MunicipioValidation;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
                        throws CustomException {

                MunicipioValidation.createValidation(createMunicipioDto, municipioRepository);

                var uf = this.ufRepository.findByCodigoUF(createMunicipioDto.codigoUF());

                if (uf.isEmpty()) {
                        throw new CustomException("Estado não encontrado");
                }

                var newMunicipio = new MunicipioEntity();
                newMunicipio.setStatus(createMunicipioDto.status());
                newMunicipio.setNome(createMunicipioDto.nome());
                newMunicipio.setUF(uf.get(0));

                this.municipioRepository.save(newMunicipio);

                var allMunicipios = this.municipioRepository.findAll();

                return allMunicipios
                                .stream()
                                .map(municipios -> new ResponseMunicipioDto(
                                                municipios.getCodigoMunicipio(),
                                                municipios.getUF().getCodigoUF(),
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
                                                municipio.getUF().getCodigoUF(),
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
                                                municipio.getUF().getCodigoUF(),
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
                                                municipio.getUF().getCodigoUF(),
                                                municipio.getNome(),
                                                municipio.getStatus(),
                                                null))
                                .toList();
        }

        public List<ResponseMunicipioDto> municipioById(Integer codigoMunicipio) {
                var listMunicipio = this.municipioRepository.findByCodigoMunicipio(codigoMunicipio);

                return listMunicipio
                                .stream()
                                .map(municipio -> new ResponseMunicipioDto(
                                                municipio.getCodigoMunicipio(),
                                                municipio.getUF().getCodigoUF(),
                                                municipio.getNome(),
                                                municipio.getStatus(),
                                                null))
                                .toList();
        }

        public List<ResponseMunicipioDto> municipuioByUF(String codigoUF) throws CustomException {
                var id = Integer.valueOf(codigoUF);
                var ufCurrent = this.ufRepository.findByCodigoUF(id);

                if (ufCurrent.isEmpty()) {
                        return new ArrayList<>();
                }

                var listMunicipio = this.municipioRepository.findByUF(ufCurrent.get(0));

                return listMunicipio
                                .stream()
                                .map(municipio -> new ResponseMunicipioDto(
                                                municipio.getCodigoMunicipio(),
                                                municipio.getUF().getCodigoUF(),
                                                municipio.getNome(),
                                                municipio.getStatus(),
                                                null))
                                .toList();

        }

        public List<ResponseMunicipioDto> updateMunicipio(UpdateMunicipioDto updateMunicipioDto)
                        throws CustomException {

                var updatedMunicipio = MunicipioValidation.updateValidation(updateMunicipioDto, municipioRepository);

                var uf = this.ufRepository.findByCodigoUF(updateMunicipioDto.codigoUF());

                if (uf.isEmpty()) {
                        throw new CustomException("Estado não encontrado");
                }

                if (updateMunicipioDto.nome() != null) {
                        updatedMunicipio.setNome(updateMunicipioDto.nome());
                }

                if (updateMunicipioDto.status() != null) {
                        updatedMunicipio.setStatus(updateMunicipioDto.status());
                }

                if (updateMunicipioDto.codigoUF() != null) {
                        updatedMunicipio.setUF(uf.get(0));
                }

                this.municipioRepository.save(updatedMunicipio);

                var allMunicipios = this.municipioRepository.findAll();

                return allMunicipios
                                .stream()
                                .map(municipios -> new ResponseMunicipioDto(
                                                municipios.getCodigoMunicipio(),
                                                municipios.getUF().getCodigoUF(),
                                                municipios.getNome(),
                                                municipios.getStatus(),
                                                null))
                                .toList();

        }
}