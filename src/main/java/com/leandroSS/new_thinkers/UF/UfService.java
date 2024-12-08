package com.leandroSS.new_thinkers.UF;

import com.leandroSS.new_thinkers.UF.dto.CreateUfDto;
import com.leandroSS.new_thinkers.UF.dto.ResponseUfDto;
import com.leandroSS.new_thinkers.UF.dto.UpdateUfDto;
import com.leandroSS.new_thinkers.utils.excepition.CustomException;

import com.leandroSS.new_thinkers.utils.validation.UfValidation;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UfService {
        private final UfRepository ufRepository;

        public UfService(UfRepository ufRepository) {
                this.ufRepository = ufRepository;
        }

        public List<ResponseUfDto> createUf(CreateUfDto createUfDto) throws CustomException {

                UfValidation.createValidation(createUfDto, ufRepository);

                var newUf = new UfEntity();
                newUf.setSigla(createUfDto.sigla());
                newUf.setNome(createUfDto.nome());
                newUf.setStatus(createUfDto.status());

                this.ufRepository.save(newUf);

                var allUf = this.ufRepository.findAll();

                return allUf
                                .stream()
                                .map(UFs -> new ResponseUfDto(
                                                UFs.getCodigoUF(),
                                                UFs.getSigla(),
                                                UFs.getNome(),
                                                UFs.getStatus()))
                                .toList();
        }

        public List<ResponseUfDto> listAllUF() {
                var allUf = this.ufRepository.findAll();

                return allUf
                                .stream()
                                .map(uf -> new ResponseUfDto(
                                                uf.getCodigoUF(),
                                                uf.getSigla(),
                                                uf.getNome(),
                                                uf.getStatus()))
                                .toList();
        }

        public List<ResponseUfDto> ufBySigla(String sigla) {

                var listUf = this.ufRepository.findBySigla(sigla);

                return listUf
                                .stream()
                                .map(uf -> new ResponseUfDto(
                                                uf.getCodigoUF(),
                                                uf.getSigla(),
                                                uf.getNome(),
                                                uf.getStatus()))
                                .toList();
        }

        public List<ResponseUfDto> ufByNome(String nome) {
                var listUf = this.ufRepository.findByNome(nome);

                return listUf
                                .stream()
                                .map(uf -> new ResponseUfDto(
                                                uf.getCodigoUF(),
                                                uf.getSigla(),
                                                uf.getNome(),
                                                uf.getStatus()))
                                .toList();
        }

        public List<ResponseUfDto> ufById(Integer codigoUF) {

                var listUf = this.ufRepository.findByCodigoUF(codigoUF);

                return listUf
                                .stream()
                                .map(uf -> new ResponseUfDto(
                                                uf.getCodigoUF(),
                                                uf.getSigla(),
                                                uf.getNome(),
                                                uf.getStatus()))
                                .toList();
        }

        public List<ResponseUfDto> ufByStatus(Integer status) {
                var listUf = this.ufRepository.findByStatus(status);

                return listUf
                                .stream()
                                .map(uf -> new ResponseUfDto(
                                                uf.getCodigoUF(),
                                                uf.getSigla(),
                                                uf.getNome(),
                                                uf.getStatus()))
                                .toList();

        }

        public List<ResponseUfDto> updateUF(UpdateUfDto updateUfDto) throws CustomException {

                var updatedUf = UfValidation.updateValidation(updateUfDto, ufRepository);

                if (updateUfDto.nome() != null) {
                        updatedUf.setNome(updateUfDto.nome());
                }

                if (updateUfDto.sigla() != null) {
                        updatedUf.setSigla(updateUfDto.sigla());
                }

                if (updateUfDto.status() != null) {
                        updatedUf.setStatus(updateUfDto.status());
                }

                this.ufRepository.save(updatedUf);

                var allUf = this.ufRepository.findAll();

                return allUf
                                .stream()
                                .map(UFs -> new ResponseUfDto(
                                                UFs.getCodigoUF(),
                                                UFs.getSigla(),
                                                UFs.getNome(),
                                                UFs.getStatus()))
                                .toList();

        }
}
