package com.leandroSS.new_thinkers.bairro.service;

import com.leandroSS.new_thinkers.bairro.dto.CreateBairroDto;
import com.leandroSS.new_thinkers.bairro.dto.ResponseBairroDto;
import com.leandroSS.new_thinkers.bairro.dto.UpdateBairroDto;
import com.leandroSS.new_thinkers.bairro.entity.BairroEntity;
import com.leandroSS.new_thinkers.bairro.repository.BairroRepository;
import com.leandroSS.new_thinkers.municipio.repository.MunicipioRepository;
import com.leandroSS.new_thinkers.excepition.CustomException;
import com.leandroSS.new_thinkers.bairro.validation.BairroValidation;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BairroService {

        private final BairroRepository bairroRepository;
        private final MunicipioRepository municipioRepository;

        public BairroService(BairroRepository bairroRepository, MunicipioRepository municipioRepository) {
                this.bairroRepository = bairroRepository;
                this.municipioRepository = municipioRepository;
        }

        public List<ResponseBairroDto> createBairro(CreateBairroDto createBairroDto) throws CustomException {

                BairroValidation.createValidation(createBairroDto, bairroRepository);

                var municipio = this.municipioRepository.findByCodigoMunicipio(createBairroDto.codigoMunicipio());

                if (municipio.isEmpty()) {
                        throw new CustomException("Municipio não encontrado");
                }

                var newBairro = new BairroEntity();
                newBairro.setStatus(createBairroDto.status());
                newBairro.setNome(createBairroDto.nome());
                newBairro.setMunicipio(municipio.get(0));

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

        public List<ResponseBairroDto> bairroById(Integer codigoBairro) {
                var listMunicipio = this.bairroRepository.findByCodigoBairro(codigoBairro);

                return listMunicipio
                                .stream()
                                .map(bairros -> new ResponseBairroDto(
                                                bairros.getCodigoBairro(),
                                                bairros.getMunicipio().getCodigoMunicipio(),
                                                bairros.getNome(),
                                                bairros.getStatus(),
                                                null))
                                .toList();
        }

        public List<ResponseBairroDto> bairroByMunicipio(String municipio) throws CustomException {
                var id = Integer.valueOf(municipio);
                var municipioCurrent = this.municipioRepository.findByCodigoMunicipio(id);

                if (municipioCurrent.isEmpty()) {
                        throw new CustomException("Municipio não encontrado");
                }

                var listBairro = this.bairroRepository.findByMunicipio(municipioCurrent.get(0));

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

        public List<ResponseBairroDto> updateBairro(UpdateBairroDto updateBairroDto)
                        throws CustomException {

                var bairroCurrent = BairroValidation.updateValidation(updateBairroDto, bairroRepository);

                var municipio = this.municipioRepository.findByCodigoMunicipio(updateBairroDto.codigoMunicipio());

                if (municipio.isEmpty()) {
                        throw new CustomException("Municipio não encontrado");
                }

                if (updateBairroDto.nome() != null) {
                        bairroCurrent.setNome(updateBairroDto.nome());
                }

                if (updateBairroDto.status() != null) {
                        bairroCurrent.setStatus(updateBairroDto.status());
                }

                if (updateBairroDto.codigoMunicipio() != null) {
                        bairroCurrent.setMunicipio(municipio.get(0));
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
