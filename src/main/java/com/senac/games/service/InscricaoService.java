package com.senac.games.service;

import com.senac.games.dto.request.inscricao.InscricaoDTORequest;
import com.senac.games.dto.response.inscricao.InscricaoDTOResponse;
import com.senac.games.entity.Inscricao;
import com.senac.games.entity.Jogo;
import com.senac.games.entity.Participante;
import com.senac.games.repository.InscricaoRepository;
import com.senac.games.repository.JogoRepository;
import com.senac.games.repository.ParticipanteRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InscricaoService {
    private InscricaoRepository inscricaoRepository;
    private final ParticipanteRepository participanteRepository;
    private final JogoRepository jogoRepository;

    @Autowired
    private ModelMapper modelMapper;

    public InscricaoService(ParticipanteRepository participanteRepository, JogoRepository jogoRepository, InscricaoRepository inscricaoRepository) {
        this.participanteRepository = participanteRepository;
        this.jogoRepository = jogoRepository;
        this.inscricaoRepository = inscricaoRepository;
    }
    @Transactional
    public InscricaoDTOResponse criarInscricao(InscricaoDTORequest inscricaoDTORequest) {
        Inscricao inscricao = new Inscricao();

        Participante participante = participanteRepository.findById(Integer.valueOf(inscricaoDTORequest.getParticipanteId()))
                .orElseThrow(() -> new EntityNotFoundException("Participante não encontrado com ID: " + inscricaoDTORequest.getParticipanteId()));

        Jogo jogo = jogoRepository.findById(Integer.valueOf(inscricaoDTORequest.getJogoId()))
                .orElseThrow(() -> new EntityNotFoundException("Jogo não encontrado com ID: " + inscricaoDTORequest.getJogoId()));

        inscricao.setData(inscricaoDTORequest.getData());
        inscricao.setStatus(inscricaoDTORequest.getStatus());
        inscricao.setParticipante(participante);
        inscricao.setJogo(jogo);

        Inscricao inscricaoSalva = inscricaoRepository.save(inscricao);

        return modelMapper.map(inscricaoSalva, InscricaoDTOResponse.class);
    }


    public List<InscricaoDTOResponse> listarInscricoesAtivos() {
        List<Inscricao> inscricoes = inscricaoRepository.listarInscricoes();
        return inscricoes.stream()
                .map(inscricao -> modelMapper.map(inscricao, InscricaoDTOResponse.class))
                .collect(Collectors.toList());
    }

    public InscricaoDTOResponse listarPorInscricaoId(Integer inscricaoId) {
        Inscricao inscricao = inscricaoRepository.obterInscricaoPeloId(inscricaoId);
        return modelMapper.map(inscricao, InscricaoDTOResponse.class);
    }
    @Transactional
    public void deletarPorInscricaoId(Integer inscricaoId) {
        if (!inscricaoRepository.existsById(inscricaoId)) {
            throw new EntityNotFoundException("Inscricao com ID " + inscricaoId + " não encontrado");
        }
        inscricaoRepository.apagadoLogicoInscricao(inscricaoId);
    }
    @Transactional
    public InscricaoDTOResponse editarPorInscricaoId(Integer inscricaoId, InscricaoDTORequest inscricaoDTORequest) {
        return inscricaoRepository.findById(inscricaoId)
                .map(inscricaoExistente -> {
                    // Atualiza apenas os campos que foram fornecidos no DTO
                    modelMapper.map(inscricaoDTORequest, inscricaoExistente);

                    Inscricao inscricaoAtualizado = inscricaoRepository.save(inscricaoExistente);
                    return modelMapper.map(inscricaoAtualizado, InscricaoDTOResponse.class);
                })
                .orElseThrow(() -> new EntityNotFoundException("Inscricao não encontrado com id " + inscricaoId));
    }
}
