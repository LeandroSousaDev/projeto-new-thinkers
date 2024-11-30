package com.leandroSS.new_thinkers.bairro;

import com.leandroSS.new_thinkers.bairro.dto.CreateBairroDto;
import com.leandroSS.new_thinkers.bairro.dto.ResponseBairroDto;
import com.leandroSS.new_thinkers.municipio.MunicipioRepository;
import com.leandroSS.new_thinkers.utils.excepition.NotFoundException;

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

        public List<ResponseBairroDto> createBairro(CreateBairroDto createBairroDto) throws NotFoundException {

                var municipio = this.municipioRepository.findByCodigoMunicipio(createBairroDto.codigoMunicipio());

                if (municipio == null) {
                        throw new NotFoundException("Municipio não encontrado");
                }

                var newBairro = new BairroEntity();
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

        public List<ResponseBairroDto> bairroByMunicipio(String municipio) throws NotFoundException {
                var id = Integer.valueOf(municipio);
                var municipioCurrent = this.municipioRepository.findByCodigoMunicipio(id);

                if (municipioCurrent == null) {
                        throw new NotFoundException("Municipio não encontrado");
                }

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

        public List<ResponseBairroDto> updateBairro(String codigoBairro, CreateBairroDto createBairroDto)
                        throws NotFoundException {

                var id = Integer.valueOf(codigoBairro);
                var bairroCurrent = this.bairroRepository.findByCodigoBairro(id);

                if (bairroCurrent == null) {
                        throw new NotFoundException("Bairro não encontrado");
                }

                var municipio = this.municipioRepository.findByCodigoMunicipio(createBairroDto.codigoMunicipio());

                if (municipio == null) {
                        throw new NotFoundException("Municipio não encontrado");
                }

                if (createBairroDto.nome() != null) {
                        bairroCurrent.setNome(createBairroDto.nome());
                }

                if (createBairroDto.status() != null) {
                        bairroCurrent.setStatus(createBairroDto.status());
                }

                if (createBairroDto.codigoMunicipio() != null) {
                        bairroCurrent.setMunicipio(municipio);
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
