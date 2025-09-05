package com.senac.games.service;

import com.senac.games.dto.request.inscricao.InscricaoDTORequest;
import com.senac.games.dto.response.inscricao.InscricaoDTOResponse;
import com.senac.games.entity.Inscricao;
import com.senac.games.repository.InscricaoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InscricaoService {
    private final InscricaoRepository inscricaoRepository;

    @Autowired
    private ModelMapper modelMapper;

    public InscricaoService(InscricaoRepository inscricaoRepository) { this.inscricaoRepository = inscricaoRepository; }

    public List<Inscricao> listarInscricoes() { return this.inscricaoRepository.listarInscricoes(); }

    public Inscricao listarInscricaoPorId(Integer inscricaoId) {
        return this.inscricaoRepository.listarInscricaoPeloId(inscricaoId);
    }

    public InscricaoDTOResponse criarInscricao(InscricaoDTORequest inscricaoDTORequest) {
        Inscricao inscricao = modelMapper.map(inscricaoDTORequest, Inscricao.class);

        Inscricao inscricaoSave = this.inscricaoRepository.save(inscricao);

        InscricaoDTOResponse inscricaoDTOResponse = modelMapper.map(inscricaoSave, InscricaoDTOResponse.class);

        return inscricaoDTOResponse;
    }

    public InscricaoDTOResponse atualizarInscricao(Integer inscricaoId, InscricaoDTORequest inscricaoDTORequest) {
        Inscricao inscricaoBuscada = this.listarInscricaoPorId(inscricaoId);

        if (inscricaoBuscada != null) {
            modelMapper.map(inscricaoDTORequest, Inscricao.class);
            Inscricao tempInscricao = inscricaoRepository.save(inscricaoBuscada);
            return modelMapper.map(tempInscricao, InscricaoDTOResponse.class);
        } else {
            return null;
        }
    }

    public void apagarInscricao(Integer inscricaoId) { inscricaoRepository.apagadoLogicoInscricao(inscricaoId); }
}
